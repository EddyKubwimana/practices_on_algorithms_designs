
def merge(array,low,high):


    lower = low
    mid = (low+high)//2
    middle = mid
    upper = high

    while lower<=mid and middle<high:

        if array[lower]>array[middle]:
            array[lower], array[middle] = array[middle+1], array[lower]
            lower+=1

        else:
            middle+=1


    return array





        



# =========testing==========#

array = [1,20,1,11,7,6,11]

print(array, merge(array,0,len(array)))



        
              
              


    
