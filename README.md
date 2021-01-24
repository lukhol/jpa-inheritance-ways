#### JPA inheritance

 - **MappedSuperClass** - fields from base class in each `@Entity` table
 - **InheritanceType.JOINED** - base fields in base table, children fields in own tables
 - **InheritanceType.SINGLE_TABLE** (discriminator value) - all in one table
 - **InheritanceType.TABLE_PER_CLASS** - fields from base + child in single table (require hibernate_sequence)
