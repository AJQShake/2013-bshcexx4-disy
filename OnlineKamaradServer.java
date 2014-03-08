
import kamarad.*;
import java.util.* ;
import java.io.* ;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class OnlineKamaradServer {

    public static void main(String args[]) {
        try{
            ORB orb = ORB.init(args, null);
            NameComponent path[] = new NameComponent[1];
            
            //Instantiating the Servant
            OnlineKamarad ok = new OnlineKamarad_Tie(new OnlineKamaradImplementation(orb)) ;
            orb.connect(ok);

            //Getting the object reference to the Name Service held in the ORB
            org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");

            //Narrowing the object reference to point to the root of the name service
            NamingContext rootCtx = NamingContextHelper.narrow(obj);

            //Creating a new name component that contains the object reference to the instantiated 
            //servant
            path[0] = new NameComponent("Kamarad", "Context");;
            //Binding the name to an object that is stored in the Name Service
            NamingContext kamaradContext = rootCtx.bind_new_context(path);
            
            path[0] = new NameComponent("OnlineKamarad", "Object");;
            kamaradContext.rebind(path, ok);

            // wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }
}

