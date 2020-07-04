package DAL.create.dao.impl;

import DAL.create.dao.ICreateTableDao;
import DAL.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTableDao implements ICreateTableDao {
    @Override
    public void createUserAccount() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "CREATE TABLE user_account(\n" +
                    "    id             serial            NOT NULL\n" +
                    "        CONSTRAINT user_pk\n" +
                    "            PRIMARY KEY,\n" +
                    "    username       varchar           NOT NULL,\n" +
                    "    password       varchar           NOT NULL,\n" +
                    "    name           varchar           NOT NULL,\n" +
                    "    classification integer DEFAULT 1 NOT NULL,\n" +
                    "    mode           integer           NOT NULL\n" +
                    ");";

            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

    @Override
    public void createPersoninfo() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "CREATE TABLE person_info\n" +
                    "(\n" +
                    "    first_name           varchar,\n" +
                    "    last_name            varchar,\n" +
                    "    id                   varchar NOT NULL\n" +
                    "        CONSTRAINT person_info_pk\n" +
                    "            PRIMARY KEY,\n" +
                    "    gender               varchar,\n" +
                    "    blood_group          varchar,\n" +
                    "    drug_allergy_history varchar,\n" +
                    "    phone_number         varchar,\n" +
                    "    e_mail               varchar,\n" +
                    "    mode                 integer\n" +
                    ");";

            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

    @Override
    public void createMedicalRecord() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "CREATE TABLE medicalrecord\n" +
                    "(\n" +
                    "    sickness    varchar,\n" +
                    "    id          serial  NOT NULL\n" +
                    "        CONSTRAINT case_pk\n" +
                    "            PRIMARY KEY,\n" +
                    "    drug        varchar,\n" +
                    "    description varchar,\n" +
                    "    mode        integer,\n" +
                    "    username    varchar NOT NULL,\n" +
                    "    date        date\n" +
                    ");";

            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

    @Override
    public void createDoctor() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "CREATE TABLE doctor\n" +
                    "(\n" +
                    "    id         varchar NOT NULL\n" +
                    "        CONSTRAINT doctor_pk\n" +
                    "            PRIMARY KEY,\n" +
                    "    name       varchar NOT NULL,\n" +
                    "    department varchar NOT NULL\n" +
                    ");";

            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }

    @Override
    public void createCalendar() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();

            String sql = "CREATE TABLE calendar\n" +
                    "(\n" +
                    "    id       varchar NOT NULL,\n" +
                    "    week     varchar NOT NULL,\n" +
                    "    ten      varchar DEFAULT 0,\n" +
                    "    eleven   varchar DEFAULT 0,\n" +
                    "    thirteen varchar DEFAULT 0,\n" +
                    "    fourteen varchar DEFAULT 0,\n" +
                    "    fifteen  varchar DEFAULT 0,\n" +
                    "    time     integer DEFAULT 0,\n" +
                    "    CONSTRAINT calendar_pk\n" +
                    "        PRIMARY KEY (id, week)\n" +
                    ");;";

            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DBUtil.close(ps, conn);
        }
    }
}
