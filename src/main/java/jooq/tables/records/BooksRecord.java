/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import javax.annotation.Generated;

import jooq.tables.Books;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BooksRecord extends UpdatableRecordImpl<BooksRecord> implements Record5<Integer, Integer, String, String, String> {

    private static final long serialVersionUID = -1438659813;

    /**
     * Setter for <code>books.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>books.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>books.language_id</code>.
     */
    public void setLanguageId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>books.language_id</code>.
     */
    public Integer getLanguageId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>books.slug</code>.
     */
    public void setSlug(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>books.slug</code>.
     */
    public String getSlug() {
        return (String) get(2);
    }

    /**
     * Setter for <code>books.name</code>.
     */
    public void setName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>books.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>books.location</code>.
     */
    public void setLocation(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>books.location</code>.
     */
    public String getLocation() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, String, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Books.BOOKS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Books.BOOKS.LANGUAGE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Books.BOOKS.SLUG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Books.BOOKS.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Books.BOOKS.LOCATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getLanguageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getSlug();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getLanguageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getSlug();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooksRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooksRecord value2(Integer value) {
        setLanguageId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooksRecord value3(String value) {
        setSlug(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooksRecord value4(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooksRecord value5(String value) {
        setLocation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooksRecord values(Integer value1, Integer value2, String value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BooksRecord
     */
    public BooksRecord() {
        super(Books.BOOKS);
    }

    /**
     * Create a detached, initialised BooksRecord
     */
    public BooksRecord(Integer id, Integer languageId, String slug, String name, String location) {
        super(Books.BOOKS);

        set(0, id);
        set(1, languageId);
        set(2, slug);
        set(3, name);
        set(4, location);
    }
}
