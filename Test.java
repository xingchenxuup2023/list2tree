package com.xcx.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<TreeNodeTest> treeNodeList = new ArrayList<>();
        treeNodeList.add(new TreeNodeTest(0L, 0L, "我是父节点0"));
        treeNodeList.add(new TreeNodeTest(1L, 0L, "我是0的子节点1"));
        treeNodeList.add(new TreeNodeTest(3L, 0L, "我是0的子节点3"));
        treeNodeList.add(new TreeNodeTest(4L, 1L, "我是1的子节点4"));
        treeNodeList.add(new TreeNodeTest(5L, 4L, "我是4的子节点5"));
        treeNodeList.add(new TreeNodeTest(6L, 6L, "我是父节点6"));
        treeNodeList.add(new TreeNodeTest(7L, 6L, "我是6的子节点7"));
        treeNodeList.add(new TreeNodeTest(8L, 6L, "我是6的子节点8"));

        // 创建树形结构（数据集合作为参数）
        TreeBuild<TreeNodeTest> treeBuild = new TreeBuild<>(treeNodeList);
        // 原查询结果转换树形结构
        treeNodeList = treeBuild.buildTree();
        System.out.println(JSON.toJSONString(treeNodeList));
    }
}
