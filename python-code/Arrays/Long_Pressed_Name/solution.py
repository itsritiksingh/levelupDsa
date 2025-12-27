"""
Topic: Arrays
Question: Long Pressed Name
Link: https://leetcode.com/problems/long-pressed-name
"""


"""
Time: O(n + m)

Space: O(1)
"""
class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        i = j = 0
        n, m = len(name), len(typed)

        while i < n and j < m:
            if name[i] != typed[j]:
                return False

            # count frequency in name
            c1 = 0
            ch = name[i]
            while i < n and name[i] == ch:
                i += 1
                c1 += 1

            # count frequency in typed
            c2 = 0
            while j < m and typed[j] == ch:
                j += 1
                c2 += 1

            if c2 < c1:
                return False

        return i == n and j == m


class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        i , j = 0, 0

        while j < len(typed):
            if i < len(name) and typed[j] == name[i]:
                i += 1
                j += 1
            
            elif j > 0 and typed[j] == typed[j -1]:
                j += 1
            
            else:
                return False
     
        return i == len(name)
