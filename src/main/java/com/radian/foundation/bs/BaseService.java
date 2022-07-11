package com.radian.foundation.bs;

import java.util.HashMap;
import java.util.Collection;




/**
 * The BaseService class contains common functionaity for
 * domain Business Service implementations. Business Services are
 * generally designed/developed on an as-needed basis by the domain
 * designer/developer based on Use Case needs.  It is generally a best
 * practice that Business Service interfaces contain coarse-grained
 * methods that perform
 * <p/>
 * This base service contains a static map of services that have already
 * been instantiated.  A sub-class implementing the BaseService would retrieve
 * an instantiated instance for itself from the cache by implementing a method
 * similar to the static instance method in the example below:
 * <br/>
 *  <pre>
 *	public class CustomerAdminDelegateRemote
 *		extends BaseService
 *		implements CustomerAdministration {
 *
 *		public CustomerAdminDelegateRemote() {
 *			super();
 *		}
 *
 *		public static CustomerAdministration instance(){
 *			return (CustomerAdministration)instance(CustomerAdminDelegateRemote.class);
 *		}
 *	}
 *
 * </pre>
 * <br/>
 * <b>NOTE:</b>Subclasses of BaseService that wish to use the framework to create
 * a Singleton instance of their service must provide a public constructor with
 * no arguments
 * <br/>
 *
 * @see com.radian.foundation.service.BaseFactory
 *
 * @author nines
 *
 */
public abstract class BaseService
{

    /**Map of instantiated Services */
    private static HashMap services = new HashMap();

    /**
     * Constructor for BaseService.
     */
    public BaseService() 
    {
        super();
    }

    /**
     * Get all of the service implementations that are being cahced
     *
     * @return Collection
     */
    public static Collection getAllServices()
    {
        return services.values();
    }

    /**
     * Returns an instance of an instantiated ServiceBase and,
     * if necessary, adds the requested ServiceBase to the static cache
     *
     * @param cl class that is being requested from the cache.
     * @return BaseService
     */
    protected static BaseService instance(Class cl)
    {

        if (!services.containsKey(cl.getName()))
        {
            //only synchronize if the key hasn't been found
            synchronized(services){

                try
                {
                    //check for the key again to make sure it hasn't been set
                    //in case more then one thread is waiting to insert the
                    //first instance of this factory...
                    if (!services.containsKey(cl.getName()))
                    {
                        Object o = cl.newInstance();
                        if (o instanceof BaseService)
                            services.put(cl.getName(),cl.newInstance());
                        else
                            throw new ServiceException("Service implementation must extend/inherit the BaseService class");
                    }
                } 
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                    throw new ServiceException(e.getMessage());
                } 
                catch (InstantiationException e)
                {
                    e.printStackTrace();
                    throw new ServiceException(e.getMessage());
                }
            }

        }

        return(BaseService)services.get(cl.getName());

    }




    public String toString(){
        return this.getClass().getName();
    }

}
