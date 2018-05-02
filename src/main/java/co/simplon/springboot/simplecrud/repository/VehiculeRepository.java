package co.simplon.springboot.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.springboot.simplecrud.model.Vehicule;

/**
 * 
 * @author NESIC Alexandre
 * interface qui hérite de JpaRepository permettant d'appeler les méthodes de gestion simplifiée des requêtes SQL pour la classe VehiculeServiceImpl
 *
 */

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

	
}

// Si nous n'utilisions pas JpaRepository il faudrait créer une interface DAO et une classe JDBC comme suit :

//public interface VehiculeDao {
//
//     //creation du contrat des méthodes à utiliser, elle seront implémenté dans JDBCLivre
//    public Vehicule<Vehicule> listVehicules() throws Exception;
//
//    public Vehicule getVehicule(Long id) throws Exception;
//
//    public Vehicule addVehicule(Vehicule vehicule) throws Exception;
//
//    public Vehicule updateVehicule(Vehicule vehicule) throws Exception;
//
//    public void deleteVehicule(Long id) throws Exception;

//@Service
//public class JdbcVehicule implements VehiculeDao {
//
//
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private DataSource datasource;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    /**
//     * Constructor
//     *
//     * @param jdbcTemplate : the JDBCTemplace connected to the Database (thanks to Spring)
//     */
//    @Autowired
//    public JdbcVehicule(JdbcTemplate jdbcTemplate) {
//        this.datasource = jdbcTemplate.getDataSource();
//    }
//
//    //Méthode pour voir toutes les infos de tous les vehicules
//    public Vehicule<Vehicule> listVehicules() throws Exception {
//        String sql;
//        ArrayList<Vehicule> aLlistOfVehicules = new ArrayList<>();
//
//        //Preparation de la requete SQL
//        sql = "SELECT *\n" + "FROM vehicule\n"
//
//        try (Connection connection = this.datasource.getConnection()) {
//            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//                try (ResultSet rs = pstmt.executeQuery()) {
//                    // Log info
//                    logSQL(pstmt);
//                    // Parcourir les resultats de la requete
//                    while (rs.next()) {
//                        vehicule = getVehiculeFromResultSet(rs);
//                        aLlistOfVehicules.add(vehicule);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    log.error("SQL Error !:" + pstmt.toString(), e);
//                    throw e;
//                }
//            }
//        }
//        return aLlistOfVehicules;
//    }
//
//    //Fabrication de l'objet avec toutes les infos à partir de la classe modèle "Vehicule"
//    private Vehicule getVehiculeFromResultSet(ResultSet rs) throws SQLException {
//        Vehicule vehicule = new Vehicule();
//        //On récupère les données par colonnes
//        vehicule.setId(rs.getLong("id"));
//        vehicule.setMarque(rs.getString("marque"));
//        vehicule.setModele(rs.getString("modele"));
//        vehicule.setCouleur(rs.getString("couleur"));
//		  vehicule.setImmatriculation(rs.getString("immatriculation"));
//
//        return vehicule;
//    }
//
//
//    // Méthode pour voir un Vehicule
//    @Override
//    public Vehicule getVehicule(Long id) throws Exception {
//        Vehicule vehicule = null;
//        String sql;
//
//        // Preparation de la requete SQL
//        sql = "SELECT *\n" +
//                "FROM vehicule\n" +
//                "WHERE vehicule.id = ?;";
//
//
//        try (Connection connection = this.datasource.getConnection()) {
//            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//                //on injecte le parametre ci dessous à la place du ? de la requete
//                pstmt.setLong(1, id);
//
//                try (ResultSet rs = pstmt.executeQuery()) {
//                    pstmt.setLong(1, id);
//                    // Log info
//                    logSQL(pstmt);
//                    // Handle the query results
//                    while (rs.next()) {
//                        vehicule = getVehiculeFromResultSet(rs);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    log.error("SQL Error !:" + pstmt.toString(), e);
//                    throw e;
//                }
//            }
//        }
//        return vehicule;
//    }
//
//    //Pour ajouter un Vehicule
//    @Override
//    public Vehicule addVehicule(Vehicule vehicule) throws Exception {
//        PreparedStatement pstmt = null;
//        Vehicule result = null;
//        try {
//            // Preparation de la requete SQL
//            String sql = "INSERT INTO vehicule (marque, modele, couleur, immatriculation) VALUES (?,?,?,?)";
//            pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//
//            //on injecte le parametre ci dessous à la place des ? de la requete
//            pstmt.setString(1, vehicule.getMarque());
//            pstmt.setLong(2, vehicule.getModele());
//            pstmt.setLong(3, vehicule.getCouleur());
//			  pstmt.setLong(4, vehicule.getImmatriculation());
//            // Log info
//            logSQL(pstmt);
//
//            // Run the the update query
//            pstmt.executeUpdate();
//
//            // recupération de l'id genere, et maj de l'acteur avec l'id et la date de modif
//            ResultSet rs = pstmt.getGeneratedKeys();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("SQL Error !:" + pstmt.toString(), e);
//            throw e;
//        } finally {
//            pstmt.close();
//        }
//
//        return result;
//
//
//    }
//
//    @Override
//    public Vehicule updateLivre(Vehicule vehicule) throws Exception {
//        Vehicule result = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            // Prepare the SQL query
//            String sql = "UPDATE vehicule SET marque = ?, modele = ?, couleur = ?, immatriculation=?, WHERE id = ?";
//            pstmt = datasource.getConnection().prepareStatement(sql);
//            pstmt.setString(1, vehicule.getMarque());
//            pstmt.setString(2, vehicule.getModele() );
//            pstmt.setString(3, vehicule.getCouleur());
//			  pstmt.setString(4, vehicule.getImmatriculation());
//            pstmt.setLong(5, vehicule.getId() );
//
//            // Log info
//            logSQL(pstmt);
//
//            // Run the the update query
//            int resultCount = pstmt.executeUpdate();
//            if(resultCount != 1)
//                throw new Exception("Vehicule inconnu !");
//
//            result = vehicule;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("SQL Error !:" + pstmt.toString(), e);
//            throw e;
//        } finally {
//            pstmt.close();
//        }
//
//        return result;
//    }
//
//
//
//
//    // Méthode pour effacer un vehicule
//    @Override
//    public void deleteVehicule(Long id) throws Exception {
//        String sql;
//        sql = "DELETE FROM vehicule WHERE vehicule.id = ?";
//
//        try (Connection connection = this.datasource.getConnection()) {
//
//            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//                pstmt.setLong(1, id);
//
//                try {
//
//                    int rs = pstmt.executeUpdate();
//                    pstmt.setLong(1, id);
//
//                    // Log info
//                    logSQL(pstmt);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    log.error("SQL Error !:" + pstmt.toString(), e);
//                    throw e;
//
//                }
//            }
//        }
//    }
//
//    //méthode pour les logs
//    private void logSQL(PreparedStatement pstmt) {
//        String sql;
//
//        if (pstmt == null)
//            return;
//
//        sql = pstmt.toString().substring(":".indexOf(pstmt.toString()) + 2);
//        log.debug(sql);
//    }
//}
