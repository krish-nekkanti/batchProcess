package com.radian.foundation.bs;

import java.util.HashMap;
import java.util.Collection;


/**
 * The BaseFactory class contains common functionaity for
 * domain ServiceFactory implementations. ServiceFactories are
 * generally designed/developed on an as-needed basis by the domain
 * designer/developer
 * <p/>
 * This base factory contains a static map of ServiceFactories that have already
 * been instantiated.  A sub-class implementing the BaseFactory would retrieve
 * an instantiated instance for itself from the cache by implementing a method
 * similar to the static instance method in the example below:
 * <br/>
 *  <pre>
 *		public class CustomerServiceFactory
 *			extends BaseFactory  {
 *
 *
 *			public static CustomerServiceFactory instance()
 * 			{
 *	 			return (CustomerServiceFactory)instance(CustomerServiceFactory.class);
 *	 		}
 *		}
 * </pre>
 * <br/>
 * <b>NOTE:</b>Subclasses of BaseFactory that wish to use the framework to create
 * a Singleton instance of their factory must provide a public constructor with
 * no arguments
 * <br/>
 *
 * @see com.radian.foundation.service.BaseService
 *
 * @author nines
 *
 */
public class BaseFactory
{

    /**The type of services that this factory should be generating - defaults to EJB service generation*/
    protected ServiceConstants.FactoryTypes.FactoryType factoryType = ServiceConstants.FactoryTypes.REMOTE_EJB;

    /**Static Map of all instantiated ServiceFactories - Service factories are generally as Singletons - this is a way to manage that in a single place in the code*/
    private static HashMap serviceFactories = new HashMap();

    /**
     * Constructor for BaseFactory.
     */
    public BaseFactory() 
    {
        super();
    }

    /**
     * Get all of the factory instances that are being cached
     *
     * @return Collection
     */
    public static Collection getAllFactories()
    {
        return serviceFactories.values();
    }


    /**
     * Returns an instance of an instantiated ServiceFactory
     * and, if necessarly, creates adds a factory to the
     * static cache
     *
     * @param cl class that is being retrieved from the cache (instantiated
     * if necessary)
     * @return BaseFactory instance of the factory class that is being requested
     */
    protected static BaseFactory instance(Class cl)
    {


        if (!serviceFactories.containsKey(cl.getName()))
        {
            //only synchronize if the key hasn't been found
            synchronized(serviceFactories){

                try
                {
                    //check for the key again to make sure it hasn't been set
                    //in case more then one thread is waiting to insert the
                    //first instance of this factory...
                    if (!serviceFactories.containsKey(cl.getName()))
                    {
                        Object o = cl.newInstance();
                        if (o instanceof BaseFactory)
                            serviceFactories.put(cl.getName(),o);
                        else
                            throw new ServiceException("Factory implementation must extend/inherit the BaseFactory class");

                    }
                } 
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                    throw new ServiceException(e.getMessage());
                } catch (InstantiationException e)
                {
                    e.printStackTrace();
                    throw new ServiceException(e.getMessage());
                }
            }
        }
        return(BaseFactory)serviceFactories.get(cl.getName());
    }




    /**
     * Returns the factoryType.
     * @return ServiceConstants.FactoryTypes.FactoryType
     */
    public ServiceConstants.FactoryTypes.FactoryType getFactoryType() {
        return factoryType;
    }

    /**
     * Sets the factoryType.
     * @param factoryType The factoryType to set
     */
    public void setFactoryType(
                              ServiceConstants.FactoryTypes.FactoryType factoryType) {

        synchronized(this.factoryType){
            this.factoryType = factoryType;
        }

    }

    public String toString(){
        return this.getClass().getName();
    }

}
