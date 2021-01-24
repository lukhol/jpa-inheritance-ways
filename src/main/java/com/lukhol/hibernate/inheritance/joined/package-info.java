package com.lukhol.hibernate.inheritance.joined;
/**
 * Inheritance - JOINED
 * It uses hibernate sequence generator because each superclass has to be properly joined with base class table.
 * Tables (3) - base + for each children class: base - Publication, subclass1 - Book, subclass2 - BlogPost.
 * Separation on DB and Java side.
 */
