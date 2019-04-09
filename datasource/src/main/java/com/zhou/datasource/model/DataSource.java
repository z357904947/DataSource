package com.zhou.datasource.model;

/**
 * @auther zhoupan
 * @date 2019/4/8 20:37
 * @info
 */
public class DataSource {
    String datasourceId;
    String url;
    String userName;
    String passWord;
    String code;
    String databasetype;

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDatabasetype() {
        return databasetype;
    }

    public void setDatabasetype(String databasetype) {
        this.databasetype = databasetype;
    }
}
