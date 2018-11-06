function [Tw, Cw, x, cont,disp] = Sor(A, b, x0,w, tol)
D = diag(diag(A));
L = tril(-1*A,-1);
U = triu(-1*A,1);
Tw = inv(D-w*L)*((1-w)*D + w*U);
Cw = w*inv(D-w*L)*b;
disp = tol + 1;
cont = 0;
while disp > tol
x1=Tw*x0+Cw;
disp = norm(x1-x0, 2);
cont = cont + 1;
x0 = x1;
end
x=x0;
end