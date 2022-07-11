package com.radian.foundation.bs;

import java.io.Serializable;

/**
 * Constants that are used by the businss service tier of the 
 * application
 * 
 *
 */
public final class ServiceConstants
{

    /**
     * Operator types that are used for setting search criteria 
     * parameters
     * 
    */
    public interface CriteriaOperatorTypes
    {

        public static final OperatorType EQUALS = new OperatorType("=");
        public static final OperatorType LESS_THAN = new OperatorType("<");
        public static final OperatorType GREATER_THAN = new OperatorType(">");
        public static final OperatorType LESS_THAN_EQ = new OperatorType("<=");
        public static final OperatorType GREATER_THAN_EQ = new OperatorType(">=");
        public static final OperatorType NOT_EQUAL = new OperatorType("!=");
        public static final OperatorType LIKE = new OperatorType("LIKE");
        public static final OperatorType IN = new OperatorType("IN");
        public static final OperatorType OR = new OperatorType("OR");

        public static final OperatorType SORT_ASCENDING = new OperatorType("ASC");
        public static final OperatorType SORT_DECENDING = new OperatorType("DESC");
        
        public static final OperatorType SORT_ASCENDING_CASESENS = new OperatorType("ASC CASE SENS");
        public static final OperatorType SORT_DECENDING_CASESENS = new OperatorType("DESC CASE SENS");
        
        public final class OperatorType implements Serializable
        {
            private String operatorType;

            public String getOperatorType(){return operatorType;}

            public OperatorType(String operatorType)
            {
                this.operatorType = operatorType;
            }

            public boolean equals(Object o)
            {

                OperatorType tp = (OperatorType)o;
                if (this.operatorType != null && 
                    this.operatorType.equals(tp.operatorType))
                {
                    return true;
                }

                return false;
            }

            public String toString()
            {
                return this.operatorType;           
            }
        }
    }

    /**
     * Types of factories that are suppored by the business service
     * tier
     * 
     */
    public interface FactoryTypes
    {


        /**Remote EJB Factory type*/    
        public FactoryType REMOTE_EJB = new FactoryType(0);

        /**Local EJB Factory type*/
        public FactoryType LOCAL_EJB = new FactoryType(1);

        /**Web Service Factory type*/
        public FactoryType WEB_SERVICE_PROXY = new FactoryType(2);

        /**Local (non distributed) factory type */
        public FactoryType LOCAL_CLASS = new FactoryType(3);

        public final class FactoryType
        {

            private int factoryType;

            private FactoryType( int i ) {
                factoryType = i;
            }

            public int getFactoryType(){return factoryType;}

            public boolean equals(Object o){

                FactoryType tp = (FactoryType)o;
                if (this.factoryType == tp.factoryType)
                {
                    return true;
                }

                return false;
            }

            public String toString(){
                if (REMOTE_EJB.getFactoryType() == factoryType)
                    return "REMOTE_EJB";
                else if (LOCAL_EJB.getFactoryType() == factoryType)
                    return "LOCAL_EJB";
                else if (WEB_SERVICE_PROXY.getFactoryType() == factoryType)
                    return "WEB_SERVICE_PROXY";
                else if (LOCAL_CLASS.getFactoryType() == factoryType)
                    return "LOCAL_CLASS";
                else
                    return "***UNKNOWN FACTORY TYPE***";
            }

        }

    } 

}
