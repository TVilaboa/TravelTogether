package model.user;

import hibernate.Main;
import hibernate.UsersEntity;
import model.Constants;
import org.hibernate.Query;
import org.hibernate.Session;
import org.securityfilter.realm.SimpleSecurityRealmBase;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 09/04/2014
 * Time: 02:19 AM
 */
public class LoginRealm extends SimpleSecurityRealmBase {

    /**
     * Trivial implementation of the SecurityRealmInterface.
     * <p/>
     * There is one user: username is 'username', password is 'password'
     * And this user is in one role: 'inthisrole'
     *
     * @author Max Cooper (max@maxcooper.com)
     * @version $Revision: 1.3 $ $Date: 2003/10/25 10:49:04 $
     */


    private String exampleProperty;

    /**
     * Authenticate a user.
     * <p/>
     * Implement this method in a subclass to avoid dealing with Principal objects.
     *
     * @param username a username
     * @param password a plain text password, as entered by the user
     * @return null if the user cannot be authenticated, otherwise a Principal object is returned
     */
    public boolean booleanAuthenticate(String username, String password) {
//        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
//        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
//        sb.applySettings(cfg.getProperties());
//        StandardServiceRegistry standardServiceRegistry = sb.build();
//        SessionFactory sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
//
//
//        Query query = sessionFactory.getCurrentSession();
        //resovler tema del email
        Session session = Main.getSession();
        String hql = "FROM UsersEntity U WHERE U.user = " + username;
        Query query = session.createQuery(hql);
        List results = query.list();
        if (results.isEmpty())
            return false;
        UsersEntity dbuser = (UsersEntity) results.get(0);
        if (dbuser.getPass().equals(password))
            return true;
        return false;

    }

    /**
     * Test for role membership.
     * <p/>
     * Implement this method in a subclass to avoid dealing with Principal objects.
     *
     * @param username The name of the user
     * @param role     name of a role to test for membership
     * @return true if the user is in the role, false otherwise
     */
    public boolean isUserInRole(String username, String role) {
        return (
                (Constants.VALID_USERNAME.equals(username) || Constants.VALID_USERNAME2.equals(username))
                        && Constants.VALID_ROLE.equals(role)
        );
    }

    /**
     * Getter for exampleProperty.
     *
     * @return the value of exampleProperty
     */
    public String getExampleProperty() {
        return exampleProperty;
    }

    /**
     * Setter for exampleProperty to deomonstrate setting realm properties from config file.
     * <p/>
     * This has no effect other than printing a message when the property is set.
     *
     * @param value example property value
     */
    public void setExampleProperty(String value) {
        exampleProperty = value;
        System.out.println(this.getClass().getName() + ": exampleProperty set to \'" + value + "\'");
    }
}

