
def binarySearch(array, target):
    '''
    - Takes an a sorted array
    - return a if the target is found
    
    '''


    if len(array)==0:

        return -1
    
    if len(array) ==1 and array[0]!=target:
        return -1

    if array[0]==target:
        return 1
    
    else:
        mid = int((len(array))/2)
        
        if array[mid]>target:
            return binarySearch(array[:mid],target)
        else:
           return binarySearch(array[mid:],target)
        



numbers =[1,2,3]
print(numbers)

print(binarySearch(numbers,100))
