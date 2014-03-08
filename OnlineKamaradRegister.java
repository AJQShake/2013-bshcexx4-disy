import kamarad.*;
import org.omg.CORBA.*;
import java.io.* ;
import java.util.* ;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class OnlineKamaradRegister {
    public static void main(String args[]) {
        try{
            ORB orb = ORB.init(args, null);

            //Obtaining the object reference to the Name Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            //Narrowing the object reference of the Name Service to the correct Type
            NamingContext rootCtx = NamingContextHelper.narrow(objRef);
            //Creating a name of the desired object that we want to get the object reference from the name server
            NameComponent[] path = new NameComponent[2];
            path[0] = new NameComponent("Kamarad", "Context");
            path[1]    = new NameComponent("OnlineKamarad", "Object");
            
            org.omg.CORBA.Object objRefKamarad = rootCtx.resolve(path);

            // Converting the retireved object reference from the Name Service to the correct type
            OnlineKamarad kamaradRef = OnlineKamaradHelper.narrow (objRefKamarad);
      
            // Prompt user for account details or get them as command line arguments
            String name = " <customer name> ";
            String address = " <customer address> ";
            String phone = " <mobile number> ";
            double initialCredit = 0.0;

            // Create our account details object
            KamaradAccountDetails accountDetails = new KamaradAccountDetails(name, address, phone, initialCredit, -1);

            // Creating an any holder that will hold the unique id parameter from the register operation call
            AnyHolder anyUniqueIdHolder = new AnyHolder();

            // Register the user
            kamaradRef.register(accountDetails, anyUniqueIdHolder);

            // Display the newly allocated user id
            Long uniqueId = anyUniqueIdHolder.value.extract_longlong();
            System.out.println("User registered as: " + uniqueId);

        } catch (Exception e) {
                System.out.println("ERROR : " + e) ;
                e.printStackTrace(System.out);
        }
    }
}
