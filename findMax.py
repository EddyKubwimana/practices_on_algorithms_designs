
# function for find the time to buy a stock and sell it for maximun profit per share


# divide method

def divide(array):
    '''
    - Takes an list
    - Return a list with two sublist of the input list
    - To find the left sublist = inputlist[0]
    - To find the right sublist  = inputlist[1]
    '''
    mid = len(array)//2

    return [array[0:mid],array[mid:]]



def computeRateChange(prices):
    '''
     - Takes an dictionaries of days and prices on the days 
     - Keys of the dictionaries should be days and type integers
     - computes the rate of change between two contaguous days
     - returns a list of rate of change
    '''
    
    rates = []

    prices = dict(sorted(prices.items(), reverse =False))


    for i in prices:
        
        if not isinstance(i, int):
            raise ValueError("The key of the dict should be integer")
        else:


            rates.append(prices[i])

    change = []

    for i in range(1,len(rates)):

        change.append(rates[i-1]-rates[i])


    return change


def maxCross(array, low, mid, high):
    left_sum = 0
    for i in array[0:mid]:
        left_sum+= i

    summ1 = 0
    max_left = mid

    for v in range(mid, low, -1):
        summ1+= array[v]

        if summ1> left_sum:
            left_sum = summ1
            max_left = v


    right_sum =0
    for c in [array[mid:]]:
        right_sum+=c

    summ2 =0
    max_right = high

    for t in range(mid,high):
        summ2+=array[t]

        if summ2>right_sum:
            right_sum = summ2
            max_right = v
    return(max_left, max_right, left_sum+right_sum)

        


        
         






def findMaximun(array,low,high):

    if high ==low:
        return(low, high, array)
    
    else:

        mid = (low+high)//2

        left_low, left_high, left_summ = findMaximun(array,low, mid+1)

        right_low, right_high, right_summ = findMaximun(array, mid+1, high)

        cross_low, cross_high, cross_summ = findMaximun(array,left_low, right_high),
        
        if left_summ>= right_high and left_summ>= cross_summ:
            return   (left_low, left_high, left_summ)
        
        elif right_summ>= left_summ and right_high >=cross_summ:
            return  (right_low, right_high, right_summ)
        else:
            return (cross_low, cross_high, cross_summ)





        




# Testing the functions

days = {
    1:10,
    2:15,
    10:20,
    3:15,
    7:8,
    5:11,
    4:10,
    6:19,
    8:5,
    9:13
}

print(computeRateChange(days))
print(divide(computeRateChange(days)))


