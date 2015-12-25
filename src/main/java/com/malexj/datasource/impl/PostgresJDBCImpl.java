//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.malexj.datasource.impl;

import com.malexj.datasource.PostgresJDBC;
import com.malexj.entity.Content;
import com.malexj.entity.Section;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PostgresJDBCImpl implements PostgresJDBC {
    private Connection connection;

    public PostgresJDBCImpl() {
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db.site", "postgres", "2687484a");
        } catch (ClassNotFoundException var2) {
            throw new RuntimeException(">>>> Exception - Class.forName(Constant.DRIVER_POSTGRES)", var2);
        } catch (SQLException var3) {
            throw new RuntimeException(">>>> Exception - DriverManager.getConnection(Constant.CONNECTING_URL,Constant.USER_NAME,Constant.USER_PASSWORD);", var3);
        }
    }

    public boolean isConnected() {
        boolean flag = false;

        try {
            flag = !this.connection.isClosed();
            return flag;
        } catch (SQLException var3) {
            throw new RuntimeException(">>>> !connection.isClosed();", var3);
        }
    }

    public List<String> getTableNames() {
        LinkedList result = new LinkedList();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = this.connection.prepareStatement("SELECT table_name FROM information_schema.tables WHERE table_schema=\'public\' AND table_type=\'BASE TABLE\'");
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                result.add(resultSet.getString("table_name"));
            }

            Collections.sort(result);
            return result;
        } catch (SQLException var8) {
            throw new RuntimeException(">>>>  public List<String> getTableNames() ;", var8);
        } finally {
            this.closeResultSet(resultSet);
            this.closePreparedStatement(statement);
        }
    }

    public List<Content> getContentList() {
        ArrayList result = new ArrayList();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = this.connection.prepareStatement("SELECT * FROM content ORDER BY id");
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Content e = new Content();
                e.setId(resultSet.getInt("id"));
                e.setName(resultSet.getString("name"));
                result.add(e);
            }
        } catch (SQLException var8) {
            throw new RuntimeException(">>>>  public List<Content> getContentList()", var8);
        } finally {
            this.closeResultSet(resultSet);
            this.closePreparedStatement(statement);
        }

        return result;
    }

    public void setContentList(List<Content> list) {
        PreparedStatement statement = null;

        try {
            Iterator e = list.iterator();

            while(e.hasNext()) {
                Content iter = (Content)e.next();
                statement = this.connection.prepareStatement("INSERT  INTO  content (name) VALUES (?)");
                statement.setString(1, iter.getName());
                statement.executeUpdate();
            }
        } catch (SQLException var8) {
            throw new RuntimeException(">>>>  public void setContentList(List<Content> list)", var8);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    public void updateContent(Content content) {
        PreparedStatement statement = null;

        try {
            statement = this.connection.prepareStatement("UPDATE content SET name=? WHERE id=? ");
            statement.setString(1, content.getName());
            statement.setInt(2, content.getId());
            statement.executeUpdate();
        } catch (SQLException var7) {
            throw new RuntimeException(">>>>   public void updateContent(Content content)", var7);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    public void clearContent() {
        PreparedStatement statement = null;

        try {
            statement = this.connection.prepareStatement("TRUNCATE TABLE content RESTART IDENTITY  CASCADE");
            statement.execute();
        } catch (SQLException var6) {
            throw new RuntimeException(">>>>   public void clearContent()", var6);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    public List<Section> getSectionList() {
        ArrayList list = new ArrayList();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = this.connection.prepareStatement("SELECT * FROM section ORDER BY id");
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Section e = new Section();
                e.setId(resultSet.getInt("id"));
                e.setName(resultSet.getString("name"));
                list.add(e);
            }
        } catch (SQLException var8) {
            throw new RuntimeException(">>>>  public List<Section> getSectionList()", var8);
        } finally {
            this.closeResultSet(resultSet);
            this.closePreparedStatement(statement);
        }

        return list;
    }

    public void setSectionList(List<Section> list) {
        PreparedStatement statement = null;

        try {
            Iterator e = list.iterator();

            while(e.hasNext()) {
                Section iter = (Section)e.next();
                statement = this.connection.prepareStatement("INSERT INTO section (name) VALUES (?) ");
                statement.setString(1, iter.getName());
                statement.executeUpdate();
            }
        } catch (SQLException var8) {
            throw new RuntimeException(">>>>  public void setSectionList(List<Section> list)", var8);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    public void updateSection(Section section) {
        PreparedStatement statement = null;

        try {
            statement = this.connection.prepareStatement("UPDATE section SET name=? WHERE id=?");
            statement.setString(1, section.getName());
            statement.setInt(2, section.getId());
            statement.executeUpdate();
        } catch (SQLException var7) {
            throw new RuntimeException(">>>>  public void updateSection(Section section)", var7);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    public void clearSection() {
        PreparedStatement statement = null;

        try {
            statement = this.connection.prepareStatement("TRUNCATE TABLE section RESTART IDENTITY  CASCADE");
            statement.execute();
        } catch (SQLException var6) {
            throw new RuntimeException(">>>>   public void clearSection()", var6);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    public List<Section> getListSectionForNameContent(String str) {
        ArrayList list = new ArrayList();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = this.connection.prepareStatement("SELECT section.id, section.name FROM content_section   JOIN content ON content.id = content_section.id_content JOIN section ON section.id = content_section.id_section WHERE content.name = ?");
            statement.setString(1, str);
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Section e = new Section();
                e.setId(resultSet.getInt("id"));
                e.setName(resultSet.getString("name"));
                list.add(e);
            }
        } catch (SQLException var9) {
            throw new RuntimeException(">>>>   public List<Section> getListSectionForNameContent(String str)", var9);
        } finally {
            this.closeResultSet(resultSet);
            this.closePreparedStatement(statement);
        }

        return list;
    }

    public void setConnectionSection(Content content, List<Section> sectionList) {
        PreparedStatement statement = null;

        try {
            if(sectionList != null) {
                Iterator e = sectionList.iterator();

                while(e.hasNext()) {
                    Section iter = (Section)e.next();
                    statement = this.connection.prepareStatement("INSERT INTO content_section (id_content, id_section) VALUES (?,?)");
                    statement.setInt(1, content.getId());
                    statement.setInt(2, iter.getId());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException var9) {
            throw new RuntimeException(">>>>   public void setConnectionSection(Content content, List<Section> sectionList)", var9);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    public void clearContentSection() {
        PreparedStatement statement = null;

        try {
            statement = this.connection.prepareStatement("TRUNCATE TABLE content_section RESTART IDENTITY  CASCADE");
            statement.execute();
        } catch (SQLException var6) {
            throw new RuntimeException(">>>>    public void clearContentSection()", var6);
        } finally {
            this.closePreparedStatement(statement);
        }

    }

    public boolean close() {
        boolean flag = false;

        try {
            if(this.connection != null) {
                this.connection.close();
                flag = true;
            }

            return flag;
        } catch (SQLException var3) {
            throw new RuntimeException(">>>> Exception - connection.close();", var3);
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException var3) {
                throw new RuntimeException(">>>>  private void closeResultSet(ResultSet resultSet)", var3);
            }
        }

    }

    private void closePreparedStatement(PreparedStatement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException var3) {
                throw new RuntimeException(">>>>   private void closePreparedStatement(PreparedStatement statement)", var3);
            }
        }

    }
}
