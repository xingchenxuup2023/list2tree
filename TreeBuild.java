package com.xcx.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * BuildTree 构建树形结构
 *
 * @author 邢晨旭
 * {@code @date} 2022/11/25 18:36
 */
public class TreeBuild<T extends BaseTreeNode<T>> {

    public static Logger logger = Logger.getLogger("TreeBuild");

    // 保存参与构建树形的所有数据
    public List<T> nodeList;
    private final List<T> exceptionList = new ArrayList<>();
    private boolean strict;


    /**
     * 构造方法
     *
     * @param nodeList 将数据集合赋值给nodeList
     */
    public TreeBuild(List<T> nodeList) {
        this.nodeList = nodeList;
        this.exceptionList.addAll(nodeList);
    }

    public TreeBuild(List<T> nodeList, boolean strict) {
        this.nodeList = nodeList;
        this.strict = strict;
        this.exceptionList.addAll(nodeList);
    }

    /**
     * 获取需构建的所有根节点（顶级节点）
     *
     * @return 所有根节点List集合
     */
    public List<T> getRootNode() {
        // 保存所有根节点的数据
        List<T> rootNodeList = new ArrayList<>();
        // 过滤根节点 从nodeList删除 减少后续循环次数
        Optional.ofNullable(nodeList).orElse(new ArrayList<>()).removeIf(item -> {
            //TODO 根据自己的规则自定义 ps：当前规则 id pid相同即为根节点 判断当前节点是否为根节点
            if (item.getParentIdAction().get().equals(item.getIdAction().get())) {
                // 是，添加
                rootNodeList.add(item);
                exceptionList.remove(item);
                return true;
            }
            return false;
        });
        return rootNodeList;
    }

    /**
     * 根据每一个顶级节点（根节点）进行构建树形结构
     *
     * @return 构建整棵树
     */
    public List<T> buildTree() {
        Optional.ofNullable(nodeList).orElse(new ArrayList<>()).forEach(BaseTreeNode::init);
        // treeNodes：保存一个根节点构建出来的完整树
        List<T> treeNodes = new ArrayList<T>();
        // getRootNode()获取所有的根节点
        Optional.ofNullable(getRootNode()).orElse(new ArrayList<>()).forEach(item -> {
            // 根节点构建子树
            item = buildChildTree(item);
            // 完成一个根节点所构建的树，增加进来
            treeNodes.add(item);
        });
        if (exceptionList.size() > 0) {
            StringBuilder s = new StringBuilder("异常数据：\n");
            exceptionList.forEach(s::append);
            if (strict) {
                throw new RuntimeException("数据不合法###" + s);
            } else {
                logger.warning(s.toString());
            }
        }
        return treeNodes;
    }

    /**
     * 递归构建子树
     *
     * @param pNode 根节点
     * @return 整棵树
     */
    public T buildChildTree(T pNode) {
        List<T> childTree = new ArrayList<T>();
        // nodeList：所有节点集合
        Optional.ofNullable(nodeList).orElse(new ArrayList<>()).forEach(item -> {
            // 判断当前节点的父节点ID是否等于根节点的ID，即当前节点为其子节点
            if (item.getParentIdAction().get().equals(pNode.getIdAction().get())) {
                // 递归调用
                childTree.add(buildChildTree(item));
                exceptionList.remove(item);
            }
        });
        // 树形构建结束
        pNode.setChildren(childTree);
        return pNode;
    }

}
