from typing import List
import collections
from heapq import heappop, heappush


class Solution:
    def splitArray(self, nums: List[int], k: int) -> int:
        lo, hi = max(nums), sum(nums)
        _min_sum = 0

        while lo < hi:
            mid = (lo + hi) // 2
            _sum, count_sub_array = 0, 1

            for i in nums:
                if _sum + i > mid:
                    count_sub_array += 1
                    _sum = i
                else:
                    _sum += i

                _min_sum = max(_sum, _min_sum)

            if count_sub_array > k:
                lo = mid + 1
            else:
                hi = mid

        return _min_sum


solution = Solution()
merged_accounts = solution.splitArray([1, 2, 3, 4, 5], 2)
