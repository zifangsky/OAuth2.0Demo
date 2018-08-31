package cn.zifangsky.model.bo;

import cn.zifangsky.model.Func;
import cn.zifangsky.model.Role;

import java.util.Objects;
import java.util.Set;

/**
 * 扩展角色类
 * @author Refresh Token
 * @date 2018/8/30
 * @since 1.0.0
 */
public class RoleBo extends Role {
    /**
     * 用户所属的角色信息
     */
    private Set<Func> funcs;

    public Set<Func> getFuncs() {
        return funcs;
    }

    public void setFuncs(Set<Func> funcs) {
        this.funcs = funcs;
    }

    @Override
    public String toString() {
        return "RoleBo{" +
                "funcs=" + funcs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoleBo roleBo = (RoleBo) o;
        return Objects.equals(funcs, roleBo.funcs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), funcs);
    }
}
