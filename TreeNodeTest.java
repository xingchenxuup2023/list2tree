package com.xcx.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreeNodeTest extends BaseTreeNode<TreeNodeTest> {
    private Long treeId;
    private Long treePId;
    private String desc;

    @Override
    public void init() {
        this.setIdAction(this::getTreeId);
        this.setParentIdAction(this::getTreePId);
    }

    @Override
    public String toString() {
        return "TreeNodeTest{" +
                "treeId=" + treeId +
                ", treePId=" + treePId +
                ", desc='" + desc + '\'' +
                '}'+"\n";
    }
}
