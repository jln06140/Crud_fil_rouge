package co.simplon.springboot.simplecrud.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.simplon.springboot.simplecrud.model.Agent;
import co.simplon.springboot.simplecrud.model.Profil;


/*
 * DAO concernant l'agent (qui est un utilisateur)
 * reliant la table "agent" de la BDD 'mist'
 */


@Repository
public class AgentRepositoryImpl implements AgentRepository {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource dataSource;
	
	@Autowired
	public void JdbcAgentDao(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	//recuperation de tous les agents
	@Override
	public List<Agent> getAllAgents() throws Exception{
		Agent agent;
		List<Agent> listeAgents = new ArrayList<Agent>();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		
		try {
			// sql pour recuperer tous les agntes de la table mist
			sql = "SELECT * FROM agent a JOIN profil p JOIN personne per on a.id_profil = p.id and per.id= a.id";
			pstmt = this.dataSource.getConnection().prepareStatement(sql);
			// execution requete
			rs = pstmt.executeQuery();
			// on parcourt les resultats
			while (rs.next()) {
				agent = getAgentFromResultSet(rs);
				listeAgents.add(agent);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return listeAgents;
	}
	
	
	//Suppresson des agents
	@Override
	public void deleteAgent(Long id) throws Exception {
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM agent where id = ?";
			pstmt = this.dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			int result = pstmt.executeUpdate();
			if(result != 1) throw new Exception("Agent non trouvé");
			log.info("resultat requete: " +result);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
	}
	
	@Override
	public Agent getAgent(Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Agent agent = null;
		try {
			String sql="SELECT * FROM agent a JOIN profil p JOIN personne per on a.id_profil = p.id and per.id= a.id where a.id= ?";
			pstmt = this.dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			log.info(pstmt.toString());
			rs = pstmt.executeQuery();

			if(rs.next()) agent = getAgentFromResultSet(rs);
		}catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return agent;
	}

	
	//Recuperation des agents (classe et table fille et "personne")
	@Override
	public Agent insertAgent(Agent agent) throws Exception {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Agent agentRes = null;
		int numeroParam = 0;
		try {
			//requete permettant de remplir les attributs de la table personne avant de remplir la table agent
			String sql = "INSERT INTO personne (nom,prenom,adresse,ville) VALUES(?,?,?,?) ";
			String sql2 = "INSERT INTO agent (id,email,motdepasse,id_profil) VALUES (?,?,?,?)";
			pstmt = this.dataSource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++numeroParam, agent.getNom());
			pstmt.setString(++numeroParam, agent.getPrenom());
			pstmt.setString(++numeroParam, agent.getAdresse());
			pstmt.setString(++numeroParam, agent.getVille());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				// Si insertion se fait correctement alors on remplit les attributs de table agent
				numeroParam = 0;
				pstmt2 = this.dataSource.getConnection().prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
				
				//on lie les clé primaires des tables agents et personne
				pstmt2.setLong(++numeroParam,rs.getLong(1));
				pstmt2.setString(++numeroParam, agent.getEmail());
				pstmt2.setString(++numeroParam, agent.getMotdepasse());
				
				//on lie egalement le profil associé a l'agent crée
				pstmt2.setInt(++numeroParam, agent.getProfil().getId());
				pstmt2.executeUpdate();
				
				
				agent.setId(rs.getLong(1));
				agentRes = agent;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		//on retourne l'agent crée avec son nouvel Id
		return agentRes;
	}

	@Override
	public Agent updateAgent(Agent agent) throws Exception {
		Agent agentRes = null;
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			String sql = "UPDATE agent a JOIN personne p on p.id = a.id SET nom = ?,"
																		+ " prenom = ?,"
																		+ " adresse = ?,"
																		+ " email = ?,"
																		+ " ville = ?,"
																		+ " motdepasse = ?,"
																		+ " id_profil = ?"
																		+ " WHERE a.id = ?";
			pstmt = this.dataSource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, agent.getNom());
			pstmt.setString(++i, agent.getPrenom());
			pstmt.setString(++i, agent.getAdresse());
			pstmt.setString(++i, agent.getEmail());
			pstmt.setString(++i, agent.getVille());
			pstmt.setString(++i, agent.getMotdepasse());
			pstmt.setInt(++i, agent.getProfil().getId());
			pstmt.setLong(++i, agent.getId());
			
			if(pstmt.executeUpdate() != 1) log.info("Agent non trouvé");
			agentRes = agent;
		}catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return agentRes;
	}
	
	private Agent getAgentFromResultSet(ResultSet rs) throws SQLException {
		Agent agent = new Agent();
		agent.setId(rs.getLong("id"));
		agent.setNom(rs.getString("nom"));
		agent.setPrenom(rs.getString("prenom"));
		agent.setAdresse(rs.getString("adresse"));
		agent.setVille(rs.getString("ville"));
		agent.setMotdepasse(rs.getString("motdepasse"));
		agent.setEmail(rs.getString("email"));
		int idProfil = rs.getInt("id_profil");
		String libelle = rs.getString("libelle");
		Profil profil = returnProfil(idProfil, libelle, agent);
		agent.setProfil(profil);

		return agent;
	}
	
	public Profil returnProfil(int id,String libelle, Agent a) {
		Profil profil = new Profil();
		profil.setId(id);
		profil.setLibelle(libelle);
		profil.addAgentToList(a);
		
		return profil;
	}

	@Override
	public Agent getAgentByName(String nom) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Agent agent = null;
		try {
			String sql="SELECT * FROM agent a JOIN profil p JOIN personne per on a.id_profil = p.id and per.id= a.id where a.nom= ?";
			pstmt = this.dataSource.getConnection().prepareStatement(sql);
			pstmt.setString(1, nom);
			log.info(pstmt.toString());
			rs = pstmt.executeQuery();

			if(rs.next()) agent = getAgentFromResultSet(rs);
		}catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return agent;
	}


	
}
