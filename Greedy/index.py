# 763. Partition Labels
# https://leetcode.com/problems/partition-labels/description/?envType=problem-list-v2&envId=ovs1lrm3
from typing import List
from collections import Counter
from heapq import heappop,heappush


class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        counter = dict()
        for idx,val in enumerate(s):
            counter[val] = idx
        
        ans = []
        max_so_far,_sum = -1,0
        for idx, val in enumerate(s):
            max_so_far = max(max_so_far,counter[val]+1)
            if max_so_far == idx+1:
                ans.append(max_so_far - _sum)
                _sum = max_so_far
        
        return ans
            
# 984. String Without AAA or BBB
# https://leetcode.com/problems/string-without-aaa-or-bbb/description/?envType=problem-list-v2&envId=ovs1lrm3

class Solution:
    def strWithout3a3b(self, a: int, b: int) -> str:
        n = a + b
        res = ""
        while n :
            if len(res) >= 2 and res[-2:] == 'aa':
                res += 'b'
                b -= 1
            elif len(res) >= 2 and res[-2:] == 'bb':
                res += 'a'
                a -= 1
            elif a > b:
                res += 'a'
                a -= 1
            else:
                res += 'b'
                b -= 1

            n -= 1
        return res

# https://leetcode.com/problems/gas-station/description/?envType=problem-list-v2&envId=ovs1lrm3
# 134. Gas Station 
class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        csum = 0
        osum = 0
        si = 0

        for i in range(len(gas)):
            delta = gas[i] - cost[i]
            osum += delta
            csum += delta

            if csum < 0:
                csum = 0
                si = i + 1

        return si if osum >= 0 else -1

# https://leetcode.com/problems/reorganize-string/description/?envType=problem-list-v2&envId=ovs1lrm3
# 767. Reorganize String
class Solution:
    def reorganizeString(self, s: str) -> str:
        counter = Counter(s)
        maxLen, maxch = 0, None
        for ch, count in counter.items():
            if count > maxLen:
                maxLen = count
                maxch = ch
        
        if maxLen > (len(s) + 1) // 2:
            return "" 
        
        ans = ['a'] * len(s)
        del counter[maxch]
        j = 0
        while maxLen > 0:
            ans[j] = maxch
            j += 2
            maxLen -= 1

        for ch, count in counter.items():
            l = count
            while l > 0:
                if j > len(s)-1:
                    j = 1
                ans[j] = ch
                j += 2
                l -= 1

        return ''.join(ans)

# 1296. Divide Array in Sets of K Consecutive Numbers
# https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/?envType=problem-list-v2&envId=ovs1lrm3

class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        frequency = {}
        for num in nums:
            frequency[num] = frequency.get(num, 0) + 1

        nums.sort()

        for num in nums:
            if frequency[num] > 0:
                for i in range(num, num + k):
                    if i not in frequency or frequency[i] == 0:
                        return False
                    frequency[i] -= 1

        return True

# https://leetcode.com/problems/car-pooling/description/?envType=problem-list-v2&envId=ovs1lrm3
# 1094. Car Pooling

class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        prefix_sum = [0] * 1001

        for trip in trips:
            [num,left,right] = trip
            prefix_sum[left] += num
            prefix_sum[right] -= num
        
        totalPass = 0

        for i in range(0,1001):
            totalPass += prefix_sum[i]
            if totalPass > capacity:
                return False
        
        return True


# https://leetcode.com/problems/jump-game-ii/submissions/1381835904/?envType=problem-list-v2&envId=ovs1lrm3
# 45. Jump Game II
class Solution:
    def jump(self, nums: List[int]) -> int:
        near = far = jumps = 0

        while far < len(nums) - 1:
            farthest = 0
            for i in range(near, far + 1):
                farthest = max(farthest, i + nums[i])
            
            near = far + 1
            far = farthest
            jumps += 1
        
        return jumps

# 55. Jump Game
# https://leetcode.com/problems/jump-game/description/?envType=problem-list-v2&envId=ovs1lrm3

class Solution:
    def canJump(self, nums):
        gas = 0
        for n in nums:
            if gas < 0:
                return False
            if n > gas:
                gas = n
            gas -= 1
        
        return True
    
# https://leetcode.com/problems/minimum-number-of-refueling-stops/description/?envType=problem-list-v2&envId=orz817us
# 871. Minimum Number of Refueling Stops
class Solution:
    def minRefuelStops(self, target: int, startFuel: int, stations: List[List[int]]) -> int:

        fuel, heap, count = startFuel, [], 0            
        
        stations.append([target, 0])                    

        while stations:
            if fuel >= target: return count             

            while stations and stations[0][0] <= fuel:  
                _, liters = stations.pop(0)
                heappush(heap,-liters)

            if not heap: return -1                     
            fuel-= heappop(heap)

            count+= 1
        