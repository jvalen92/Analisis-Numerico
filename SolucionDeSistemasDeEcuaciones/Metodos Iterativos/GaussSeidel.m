function [Tg, Cg, x, cont,disp] = gaussSeidel(A, b, x0, tol)
D = diag(diag(A));
L = tril(-1*A,-1);
U = triu(-1*A,1);
Tg = inv(D-L)*U;
Cg = inv(D-L)*b;
disp = tol + 1;
cont = 0;
while disp > tol
x1=Tg*x0+Cg;
disp = norm(x1-x0, 2);
x0 = x1
cont = cont + 1;
end
x=x0;
end
