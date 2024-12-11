//Programming Assignment 7: C++ unordered_map container
//Fall 2023 CS2433
//Dr. Churchill
//Due: Saturday, November 18, 2023, 11:59PM
//Submitted: Saturday, November 18, 2023, 8:45 PM
//Drake Geeteh. CSX ID: dgeeteh
//Program extends program 6. Adds the @# functionality which prints the stack,
//all defined variables with their current values with headers.

#include <iostream>
#include <cmath>
#include <string>
#include <cstring>
#include <unordered_map>

struct tnode {
    float val;
    tnode* next;
};

tnode* myFree = nullptr;
tnode* stack = nullptr;

void push(float a) {
    tnode* pnode;
    if (myFree == nullptr) {
        pnode = new tnode;
    }
    else {
        pnode = myFree;
        myFree = pnode->next;
    }
    pnode->val = a;
    pnode->next = stack;
    stack = pnode;
    return;
}

float pop() {
    tnode* pnode;
    if (stack == nullptr) {
        std::cerr << "Stack Underflow Error!" << std::endl;
        exit(1);
    }
    pnode = stack;
    stack = pnode->next;
    pnode->next = myFree;
    myFree = pnode;
    return pnode->val;
}

struct tcvNode {
    int type;
    float value;
    std::string name;
    tcvNode* next;
};

tcvNode* constVarList = nullptr;

const float pi = 3.14159265358979323846;
const float eps = 2.718281828459045;

void printValue(float val) {
    std::cout << val << std::endl;
}

void printText(const char* text) {
    std::cout << text << std::endl;
}

void printVariable(const char* name) {
    tcvNode* current = constVarList;
    while (current != nullptr) {
        if (std::strcmp(current->name.c_str(), name) == 0) {
            std::cout << current->value << std::endl;
            return;
        }
        current = current->next;
    }
    std::cerr << "Error: Undefined constant or variable '" << name << "'" << std::endl;
    exit(1);



}


//New to program 7. defines unordered_map which holds both constants and variables.
std::unordered_map<std::string, float> constVarMap;
float findConstVarValue(const std::string& name) {
    // Check the unordered_map for the value
    auto it = constVarMap.find(name);
    if (it != constVarMap.end()) {
        return it->second;
    }

    // If not found, display an error
    std::cerr << "Error: Undefined constant or variable '" << name << "'" << std::endl;
    exit(1);
}

// New to program 7. Function to print the stack, constants, and variables.
// Called in the switch case "@".
void printValues() {
    //Prints stack
    std::cout << "Stack: ";
    tnode* current = stack;
    while (current != nullptr) {
        std::cout << current->val << " ";
        current = current->next;
    }
    std::cout << std::endl;

    // Print constants
    std::cout << "Constants: ";
    for (const auto& entry : constVarMap) {
        std::cout << entry.first << "=" << entry.second << " ";
    }
    std::cout << std::endl;

    // Print variables
    std::cout << "Variables: ";
    tcvNode* node = constVarList;
    while (node != nullptr) {
        std::cout << node->name << "=" << node->value << " ";
        node = node->next;
    }
    std::cout << std::endl;
}


int main(int argc, char* argv[]) {
    char input[100];
    float a, b, x, y, val;
    std::string name;
    tcvNode* node;
    tcvNode* newNode = nullptr;

    while (1) {
        std::cin >> input;
        switch (input[0]) {
        case '#':
            if (std::strcmp(input, "#stop") == 0) {
                if (stack == nullptr) {
                    std::cerr << "Empty Stack Error!" << std::endl;
                    exit(1);
                }
                else {
                    while (stack != nullptr) {
                        std::cout << stack->val << "  ";
                        pop();
                    }
                    std::cout << std::endl;
                    exit(0);
                }
            }
            break;

        case '+':
        case '-':
        case '*':
        case '/':
            if (stack == nullptr) {
                std::cerr << "Stack Underflow Error!" << std::endl;
                exit(1);
            }
            a = pop();
            if (stack == nullptr) {
                std::cerr << "Stack Underflow Error!" << std::endl;
                exit(1);
            }
            b = pop();
            switch (input[0]) {
            case '+':
                push(b + a);
                break;
            case '-':
                push(b - a);
                break;
            case '*':
                push(b * a);
                break;
            case '/':
                if (a == 0) {
                    std::cerr << "Division by zero error!" << std::endl;
                    exit(1);
                }
                push(b / a);
                break;
            }
            break;

        case '^':
            if (stack == nullptr) {
                std::cerr << "Stack Underflow Error!" << std::endl;
                exit(1);
            }
            y = pop();
            if (stack == nullptr) {
                std::cerr << "Stack Underflow Error!" << std::endl;
                exit(1);
            }
            x = pop();
            push(std::pow(x, y));
            break;

        case '&':
            if (stack == nullptr) {
                std::cerr << "Stack Underflow Error!" << std::endl;
                exit(1);
            }
            x = pop();
            push(x);
            push(x);
            break;

        case '~':
            if (stack == nullptr) {
                std::cerr << "Stack Underflow Error!" << std::endl;
                exit(1);
            }
            x = pop();
            push(-x);
            break;

        case '\'':
            if (stack == nullptr) {
                std::cerr << "Stack Underflow Error!" << std::endl;
                exit(1);
            }
            x = pop();
            if (x == 0) {
                std::cerr << "Division by zero error!" << std::endl;
                exit(1);
            }
            push(1.0 / x);
            break;

        case '%':
            if (stack == nullptr || stack->next == nullptr) {
                std::cerr << "Stack Underflow Error!" << std::endl;
                exit(1);
            }
            x = pop();
            y = pop();
            push(x);
            push(y);
            break;

        case '!':
            name = input + 1;
            val = pop();
            node = constVarList;
            while (node != nullptr) {
                if (node->name == name) {
                    std::cerr << "Error: Name '" << name << "' is already defined as a constant" << std::endl;
                    exit(1);
                }
                node = node->next;
            }
            newNode = new tcvNode;
            newNode->type = 0; // 0 for constant
            newNode->value = val;
            newNode->name = name;
            newNode->next = constVarList;
            constVarList = newNode;
            break;

        case '$':
            name = input + 1;
            val = pop();
            node = constVarList;
            while (node != nullptr) {
                if (node->name == name) {
                    node->value = val;
                    break;
                }
                node = node->next;
            }
            if (node == nullptr) {
                newNode = new tcvNode;
                newNode->type = 1; // 1 for variable
                newNode->value = val;
                newNode->name = name;
                newNode->next = constVarList;
                constVarList = newNode;
            }
            break;

	
	case 's':
    if (input[1] == 'i' && input[2] == 'n') {
        if (stack == nullptr) {
            std::cerr << "Stack Underflow Error!" << std::endl;
            exit(1);
        }
        x = pop();
        push(std::sin(x));
    }
    else if (input[1] == 'q' && input[2] == 'r' && input[3] == 't') {
        if (stack == nullptr) {
            std::cerr << "Stack Underflow Error!" << std::endl;
            exit(1);
        }
        x = pop();
        if (x < 0) {
            std::cerr << "Square root of a negative number is undefined!" << std::endl;
            exit(1);
        }
        push(std::sqrt(x));
    }
    else {
        std::cerr << "Unknown operation: " << input << std::endl;
        exit(1);
    }
    break;

case 'c':
    if (input[1] == 'o' && input[2] == 's') {
        if (stack == nullptr) {
            std::cerr << "Stack Underflow Error!" << std::endl;
            exit(1);
        }
        x = pop();
        push(std::cos(x));
    }
    else {
        std::cerr << "Unknown operation: " << input << std::endl;
        exit(1);
    }
    break;

case 't':
    if (input[1] == 'a' && input[2] == 'n') {
        if (stack == nullptr) {
            std::cerr << "Stack Underflow Error!" << std::endl;
            exit(1);
        }
        x = pop();
        push(std::tan(x));
    }
    else {
        std::cerr << "Unknown operation: " << input << std::endl;
        exit(1);
    }
    break;

//New to program 7.
case '@':
    if (input[1] == '#') {
        // New: Print all values
        printValues();
    } else if (input[1] == '!') {
        printValue(pop());
    } else if (input[1] == '"') {
        if (strlen(input) > 2) {
            printText(input + 2);
        } else {
            std::cerr << "Text is missing." << std::endl;
        }
    } else if (strlen(input) > 1) {
        printVariable(input + 1);
    } else {
        std::cerr << "Invalid @ operator." << std::endl;
        exit(1);
    }
    break;
default:
            if (std::sscanf(input, "%f", &val) == 1) {
                push(val);
            }
            else {
                push(findConstVarValue(input));
            }
            break;
        }
    }

    return 0;
}
