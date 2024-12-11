# Drake Geeteh, Programming Assignment 5
# Description: Constructs a binary tree from console input, adding lexically ordered words.
# Usage: ruby mybitree.rb
# Special command: "#stop" to terminate input and display the tree in lexical order.


class TreeNode
  attr_accessor :data, :left, :right

  def initialize(data)
    @data = data
    @left = nil
    @right = nil
  end
end

class BinaryTree
  def initialize
    @root = nil
  end

  def insert(word)
    @root = insert_recursive(@root, word)
  end

  def insert_recursive(node, word)
    return TreeNode.new(word) if node.nil?
    if word < node.data
      node.left = insert_recursive(node.left, word)
    elsif word > node.data
      node.right = insert_recursive(node.right, word)
    end
    node
  end

  def inorder
    inorder_recursive(@root)
  end

  private

  def inorder_recursive(node)
    return if node.nil?
    inorder_recursive(node.left)
    puts node.data
    inorder_recursive(node.right)
  end
end

def build_tree
  tree = BinaryTree.new
  puts "Enter words (type '#stop' to finish):"
  while (input = gets.chomp)
    break if input == "#stop"
    words = input.scan(/\b[a-zA-Z]+\b/)
    words.each { |word| tree.insert(word) }
  end
  puts "Binary tree in lexical order:"
  tree.inorder
end

build_tree
