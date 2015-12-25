package com.malexj.datasource;

import com.malexj.entity.Content;
import com.malexj.entity.Section;
import java.util.List;

public interface PostgresJDBC {
    void connect();

    boolean close();

    boolean isConnected();

    List<String> getTableNames();

    List<Content> getContentList();

    void setContentList(List<Content> var1);

    void updateContent(Content var1);

    void clearContent();

    List<Section> getSectionList();

    void setSectionList(List<Section> var1);

    void updateSection(Section var1);

    void clearSection();

    List<Section> getListSectionForNameContent(String var1);

    void clearContentSection();

    void setConnectionSection(Content var1, List<Section> var2);
}
