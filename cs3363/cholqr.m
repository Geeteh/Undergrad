function [Y, diff_A, diff_B, C, D] = cholqr(X)
% CHOLQR: Performs Cholesky and QR decompositions on a given matrix X
%
% INPUT:
%   X - n x n positive definite matrix, where n is an even integer > 9
%
% OUTPUT:
%   Y       - Positive definite matrix computed as X' * X
%   diff_A  - Difference between Y and matrix A (Y - A)
%   diff_B  - Difference between Y and matrix B (Y - B)
%   C       - Cholesky decomposition of Y such that Y = C' * C
%   D       - Cholesky decomposition of the top-left 5x5 slice of Y
%
% Example usage:
%   X = rand(10);
%   [Y, diff_A, diff_B, C, D] = cholqr(X);

    % Positive definite matrix Y
    Y = X' * X;

    % Cholesky decomposition on Y
    C = chol(Y); % Y = C' * C

    % QR decomposition on input matrix X
    [Q, R] = qr(X);

    % Generate A as C' * C
    A = C' * C;

    % Generate B as Q * R
    B = Q * R;

    % Extract the upper-left 5x5 slice of Y
    Y_slice = Y(1:5, 1:5);

    % Cholesky decompisition on the 5x5 slice of Y
    D = chol(Y_slice);

    % Differences Y-A and Y-B
    diff_A = Y - A;
    diff_B = Y - B;

    % Documentation for observations:
    % Matrix D obtained from Cholesky decomposition of the upper-left 5x5 slice
    % will have the property that D' * D equals the slice of Y.
    % Since Y - A and Y - B involve small numerical differences, the upper-right
    % and lower-left quarters are likely to have small values close to 0.
end
