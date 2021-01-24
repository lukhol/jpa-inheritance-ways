package com.lukhol.hibernate.inheritance.discriminator;
/**
 * Inheritance - SINGLE_TABLE with DiscriminatorValue
 * In java MobileApplication, CustomerMobileApplication, AdminMobileApplication
 * In java History - List<MobileApplication>
 * DB - all columns in one table, additionally table has column from discriminator, in this case "type".
 * Inheritance on Java level, not DB level (single table for all).
 */
