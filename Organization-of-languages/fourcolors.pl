% fourcolors.pl
% Name: Drake Geeteh. CSX ID: dgeeteh
% Date: December 7, 2024
% Description: A Prolog program to solve the four-color map problem for South America. 
% Each country is assigned one of four colors (red, grn, blu, ylo) which will ensure that no two
% adjacent countries share the same color.

countries([
    arg, % Argentina
    bol, % Bolivia
    bra, % Brazil
    chl, % Chile
    col, % Colombia
    ecu, % Ecuador
    guy, % Guyana
    pry, % Paraguay
    per, % Peru
    sur, % Suriname
    ury, % Uruguay
    ven  % Venezuela
]).

adjacent(arg, bol).
adjacent(arg, bra).
adjacent(arg, chl).
adjacent(arg, pry).
adjacent(arg, ury).
adjacent(bol, bra).
adjacent(bol, chl).
adjacent(bol, pry).
adjacent(bol, per).
adjacent(bra, col).
adjacent(bra, guy).
adjacent(bra, pry).
adjacent(bra, sur).
adjacent(bra, ury).
adjacent(bra, ven).
adjacent(chl, per).
adjacent(col, ecu).
adjacent(col, per).
adjacent(col, ven).
adjacent(ecu, per).
adjacent(guy, sur).
adjacent(guy, ven).
adjacent(pry, ury).
adjacent(sur, ven).

color(red).
color(grn).
color(blu).
color(ylo).

no_conflict(C1-Color1, C2-Color2) :-
    adjacent(C1, C2),
    Color1 \= Color2.

solve_coloring(Assignment) :-
    countries(Countries),
    maplist(assign_color, Countries, Assignment),
    \+ (member(C1-Color1, Assignment), member(C2-Color2, Assignment), no_conflict(C1-Color1, C2-Color2)).

assign_color(Country, Country-Color) :-
    color(Color).

print_solution :-
    solve_coloring(Assignment),
    print_assignment(Assignment).

print_assignment([]).
print_assignment([Country-Color | Rest]) :-
    write(Country), write(' - '), write(Color), nl,
    print_assignment(Rest).
