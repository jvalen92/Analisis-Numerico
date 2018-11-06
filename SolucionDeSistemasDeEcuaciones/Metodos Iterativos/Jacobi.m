function [Tj, Cj, x, cont,disp] = jacobi(A, b, x0, tol)
D = diag(diag(A));
L = tril(-1*A,-1);
U = triu(-1*A,1);
Tj = inv(D)*(L+U);
Cj = inv(D)*b;
disp = tol + 1;
cont = 0;
while disp > tol
x1=Tj*x0+Cj;
disp = norm(x1-x0, 2);
x0 = x1
cont = cont + 1;
end
x=x0;
end