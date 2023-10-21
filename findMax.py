
# function for find the time to buy a stock and sell it for maximun profit per share


# divide method

def divide(array):
    '''
    - Takes an list
    - Return a list with two sublist of the input list
    - To find the left sublist = inputlist[0]
    - To find the right sublist  = inputlist[1]
    '''
    mid = (len(array)//2)+1

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
print(divide(computeRateChange(days))[1])


