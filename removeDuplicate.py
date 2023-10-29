
def removeDuplicate(array):
    '''

    - Takes an array
    - removes duplicates
    
    '''

    array.sort()

    n = len(array)-1
    i = 1
    while i<n:

        if array[i-1] == array[i]:
            del array[i-1]
            n = len(array)

        else:

            i+= 1


    return array


def rotate(array, number):

    '''
    * Takes a number of ratotation you want to apply to a list
    * Return a rotated list
    
    '''

    rem = number%len(array)
    cut = len(array)-rem

    return array[cut:]+array[:cut]



def recursivePower(number, power):
    '''
    - Compute recursive linear power
    
    '''

    if power ==0:
      return 1
    
    elif power == 1:
        return number
    else:
       
            power = power-1
            return number*recursivePower(number,power)
    

def sumLinear(lastNumber):
    if lastNumber==0:
        return lastNumber
    else:
        return lastNumber+sumLinear(lastNumber-1)
    
        
    





# testing the function


array = [1,1,1,1,22,10,6,9,0,10,6]

print(removeDuplicate(array))
print(rotate(array,5))

print(recursivePower(7,10))
print(sumLinear(5))





        