from scipy import interpolate 
import numpy as np
import matplotlib.pyplot as plt
xi =[
    1.0000,    
    2.0000,    
    3.0000,    
    4.0000,    
    5.0000,    
    6.0000,    
    7.0000,    
    8.0000,    
    9.0000,    
   10.0000 ]
fi = [
    0.5949,
    0.2622,
    0.6028,
    0.7112,
    0.2217,
    0.1174,
    0.2967,
    0.3188,
    0.4242,
    0.5079]

f = interpolate.interp1d(xi,fi,"cubic")
xnew = np.arange(1,10, 0.1)
ynew = f(xnew)
plt.plot(xi, fi, 'o', xnew, ynew, '-')
plt.show()