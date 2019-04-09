# Predictive Search Tree
Time for processing queries with tree: 90 ms*  
Time for processing queries with database: 970 000 ms*  

*75 000 rows and 26 000 queries

##Installation and getting started:
  
1\.	Download predictive-search-tree-core module from GitHub (https://github.com/wgdetective/predictiveSearchTree/tree/develop) and add maven dependency:
```
<dependency>  
    <groupId>com.hematit</groupId>  
    <artifactId>predictive-search-tree-core</artifactId>  
    <version>1.0-SNAPSHOT</version>  
</dependency>
```
2\.	Implement DataProvider interface – used to fetch data from source (file, database). Examples in predictive-search-tree-db and predictive-search-tree-file modules.

3\.	Initialize PredictiveSearchTreeFactory and DataProvider. Spring example:  
```    
@Autowired  
private final DataProvider dataProvider;  
@Autowired  
private final PredictiveSearchTreeFactory treeFactory;
```

4\.	Declare tree root node with PredictiveSearchTreeFactory.createTree method, pass DataProvider.getAllData value as a parameter:  
```
private TreeNode rootNode = treeFactory.createTree(dataProvider.getAllData());
```

5\.	For perform search call TreeNode.search method, pass search value as parameter:  
```
final List<SearchResult> result = rootNode.search(text);
```