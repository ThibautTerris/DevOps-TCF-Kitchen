package arquillian;



import fr.unice.polytech.isa.tcf.asynchronous.KitchenPrinter;
import fr.unice.polytech.isa.tcf.components.KitchenBean;

import fr.unice.polytech.isa.tcf.entities.Cookies;
import fr.unice.polytech.isa.tcf.entities.Customer;


import fr.unice.polytech.isa.tcf.entities.Order;
import fr.unice.polytech.isa.tcf.exceptions.EmptyCartException;
import fr.unice.polytech.isa.tcf.interceptors.LogParameters;
import fr.unice.polytech.isa.tcf.managed.TrackerBean;
import fr.unice.polytech.isa.tcf.utils.Database;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;

public abstract class AbstractTCFTest {


    @EJB
    protected Database memory;

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Utils
                .addPackage(Database.class.getPackage())
                // Entities
                .addPackage(Customer.class.getPackage())
                // Entities
                .addPackage(Cookies.class.getPackage())
                // Components Interfaces
                .addPackage(TrackerBean.class.getPackage())
                // Components Interfaces
                .addPackage(KitchenPrinter.class.getPackage())
                // Components Interfaces
                .addPackage(KitchenBean.class.getPackage())
                // Interceptors
                .addPackage(LogParameters.class.getPackage())
                // Exceptions
                .addPackage(EmptyCartException.class.getPackage())
                // Persistence file
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

}
