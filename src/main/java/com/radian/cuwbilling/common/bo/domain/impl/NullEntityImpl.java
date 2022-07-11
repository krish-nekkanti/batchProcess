/**
 * @(#) AxiomEntityImpl.java
 */

package com.radian.cuwbilling.common.bo.domain.impl;

import com.radian.cuwbilling.common.bo.domain.AxiomEntity;

/**
 * This class represents the abstract Entity class used by all Axiom reference
 * domain objects.
 * 
 * The Entity class provides the basic behavoir shared by all Axiom reference
 * domain objects including, but not limited to Customer, Branch, Contract and
 * Person.
 * 
 * The default behavior includes:
 * <UL>
 * <LI>Ability to activate/deactive the business entity</LI>
 * <LI>Ability to maintain a version history for the business entity.</LI>
 * </UL>
 * 
 * @author Rick Mohr
 * @version 1.0
 */
public class NullEntityImpl extends AxiomEntityImpl implements AxiomEntity
{

}
