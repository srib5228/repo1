package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FamilyTree {
    private static final String TAB = "\t";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) {
        String entireFamilyTreeStringPipeDelimited = null;
        if (args.length > 0) {
            entireFamilyTreeStringPipeDelimited = args[0];
        }

        if (entireFamilyTreeStringPipeDelimited == null) {
            //if no args passed, hardcoding to given value
            entireFamilyTreeStringPipeDelimited = "null,0,grandpa|0,1,son|0,2,daugther|1,3,grandkid|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid";
            //entireFamilyTreeStringPipeDelimited = "0,1,son|0,2,daugther|null,0,grandpa|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid|1,3,grandkid|6,7,greatgreatgrandkid1|6,8,greatgreatgrandkid2|8,9,greatgreatgreatgrandkid";
        }

        String[] familyTreeNodeStringsCommaDelimited = entireFamilyTreeStringPipeDelimited.split(Pattern.quote("|"));
        if (familyTreeNodeStringsCommaDelimited.length == 0) {
            System.out.println("Given string doesn't match the expected pattern , entireFamilyTreeStringPipeDelimited=" + entireFamilyTreeStringPipeDelimited);
            return;
        }

        Map<String, List<FamilyTreeNode>> familyTreeNodeMap = new HashMap<>();
        for (String familyTreeNodeStringCommaDelimited : familyTreeNodeStringsCommaDelimited) {
            //System.out.println("familyTreeNodeStringCommaDelimited" + familyTreeNodeStringCommaDelimited);
            String[] familyTreeNodeValues = familyTreeNodeStringCommaDelimited.split(",");
            if (familyTreeNodeValues.length < 3) {
                System.out.println("Given string doesn't match the expected pattern , familyTreeNodeStringCommaDelimited=" + familyTreeNodeStringCommaDelimited);
                return;
            }
            String parent_id = familyTreeNodeValues[0];
            String node_id = familyTreeNodeValues[1];
            String node_name = familyTreeNodeValues[2];
            //System.out.println(parent_id + " : " + node_id + " : " + node_name);
            FamilyTreeNode familyTreeNode = new FamilyTreeNode(parent_id, node_id, node_name);
            List<FamilyTreeNode> familyTreeNodeList = familyTreeNodeMap.computeIfAbsent(parent_id, k -> new ArrayList<>());
            familyTreeNodeList.add(familyTreeNode);
        }

        List<FamilyTreeNode> familyTreeNodeRootList = familyTreeNodeMap.remove("null");
        if (familyTreeNodeRootList.size() != 1) {
            System.out.println("Given string doesn't match the expected pattern , entireFamilyTreeStringPipeDelimited=" + entireFamilyTreeStringPipeDelimited);
            return;
        }
        FamilyTreeNode familyTreeNodeRoot = familyTreeNodeRootList.get(0);
        //System.out.println(familyTreeNodeRoot.getParent_id() + "\t" + familyTreeNodeRoot.getNode_id() + "\t" + familyTreeNodeRoot.getNode_name());
        StringBuffer familyTreeHeirarchyStringBuffer = new StringBuffer();
        familyTreeHeirarchyStringBuffer.append(familyTreeNodeRoot.toString()).append(NEW_LINE);
        printFamilyTreeChildNodes(familyTreeNodeRoot, familyTreeNodeMap, familyTreeHeirarchyStringBuffer, TAB);
        System.out.println(familyTreeHeirarchyStringBuffer);
    }

    private static void printFamilyTreeChildNodes(FamilyTreeNode parentFamilyTreeNode, Map<String, List<FamilyTreeNode>> familyTreeNodeMap, StringBuffer familyTreeHeirarchyStringBuffer, String indent) {
        List<FamilyTreeNode> familyTreeChildNodeList = familyTreeNodeMap.remove(parentFamilyTreeNode.getNode_id());
        if (familyTreeChildNodeList != null && !familyTreeChildNodeList.isEmpty()) {
            for (FamilyTreeNode familyTreeChildNode : familyTreeChildNodeList) {
                familyTreeHeirarchyStringBuffer.append(indent).append(familyTreeChildNode.toString()).append(NEW_LINE);
                 printFamilyTreeChildNodes(familyTreeChildNode, familyTreeNodeMap, familyTreeHeirarchyStringBuffer, indent + TAB);
            }
        }
    }
}
