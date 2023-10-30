

def gdc(a,b):
    '''
    - Takes two numbers
    - return gdc of the two numbers
    
    '''

    if a%b==0:
        return b
    else:

        remainder = a%b
        return gdc(b, remainder)
    

# testing

print(gdc(60,25))

