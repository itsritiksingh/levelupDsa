#  https://leetcode.com/problems/accounts-merge/description/
class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        size = len(accounts)

        # Create a UnionFind object
        uf = UnionFind(size)

        # Hash map to store email and its corresponding account index
        emailToId = {}
        for i, details in enumerate(accounts):
            for j in range(1, len(details)):
                email = details[j]
                if email in emailToId:
                    uf.union(i, emailToId[email])
                else:
                    emailToId[email] = i

        # Hash map to store emails under their root account
        idToEmails = {}
        for email, index in emailToId.items():
            root = uf.root(index)
            if root not in idToEmails:
                idToEmails[root] = []
            idToEmails[root].append(email)

        # Create merged accounts list
        mergedDetails = []
        for root, emails in idToEmails.items():
            # Sort emails and prepend account name
            emails.sort()
            mergedDetails.append([accounts[root][0]] + emails)

        return mergedDetails


class UnionFind:
    def __init__(self, num):
        self.parent = [i for i in range(num)]
        self.weight = [1 for _ in range(num)]

    def root(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.root(self.parent[x])
        return self.parent[x]

    def union(self, x, y):
        rootX = self.root(x)
        rootY = self.root(y)

        if rootX == rootY:
            return

        if self.weight[rootX] > self.weight[rootY]:
            self.parent[rootY] = rootX
            self.weight[rootX] += self.weight[rootY]
        else:
            self.parent[rootX] = rootY
            self.weight[rootY] += self.weight[rootX]
