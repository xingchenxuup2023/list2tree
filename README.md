# 数据集合转树工具

* 要求jdk版本1.8+

## 使用方法

1. 需要转树的类继承抽象类BaseTreeNode（已提供Children集合） 并实现init方法

   ~~~java
   
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
   ~~~

3. 创建TreeBuild（数据集合作为参数）
   ~~~java
   List<TreeNodeTest> treeNodeList = new ArrayList<>();
   treeNodeList.add(new TreeNodeTest(0L, 0L, "我是父节点0"));
   treeNodeList.add(new TreeNodeTest(1L, 0L, "我是0的子节点1"));
   treeNodeList.add(new TreeNodeTest(3L, 0L, "我是0的子节点3"));
   treeNodeList.add(new TreeNodeTest(4L, 1L, "我是1的子节点4"));
   treeNodeList.add(new TreeNodeTest(5L, 4L, "我是4的子节点5"));
   treeNodeList.add(new TreeNodeTest(6L, 6L, "我是父节点6"));
   treeNodeList.add(new TreeNodeTest(7L, 6L, "我是6的子节点7"));
   treeNodeList.add(new TreeNodeTest(8L, 6L, "我是6的子节点8"));
   TreeBuild<TreeNodeTest> treeBuild = new TreeBuild<>(treeNodeList);
   ~~~
3. 调用treeBuild.buildTree()方法构建树
   ~~~java
   treeNodeList = treeBuild.buildTree();
   ~~~