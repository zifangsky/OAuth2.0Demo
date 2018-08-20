package cn.zifangsky.model;

public class AuthScope {
    private Integer id;
    /**
     * 可被访问的用户的权限范围，比如：basic、super
     */
    private String scopeName;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName == null ? null : scopeName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}