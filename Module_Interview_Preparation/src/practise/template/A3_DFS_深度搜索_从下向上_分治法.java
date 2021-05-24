package practise.template;

public class A3_DFS_深度搜索_从下向上_分治法 {

    /*
    // V2：通过分治法遍历
    func preorderTraversal(root *TreeNode) []int {
        result := divideAndConquer(root)
        return result
    }
    func divideAndConquer(root *TreeNode) []int {
        result := make([]int, 0)
        // 返回条件(null & leaf)
        if root == nil {
            return result
        }
        // 分治(Divide)
        left := divideAndConquer(root.Left)
        right := divideAndConquer(root.Right)
        // 合并结果(Conquer)
        result = append(result, root.Val)
        result = append(result, left...)
        result = append(result, right...)
        return result
    }
    */
}
