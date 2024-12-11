% Compile: erlc chat_server.erl chat_client.erl chat_script.erl
% Execute: erl -noshell -s chat_script start -s init stop
-module(chat_script).
-export([start/0]).

start() ->
    io:format("Starting chat server and clients...~n"),
    
    %% Start the chat server if it is not already running
    ChatServer = case whereis(chat_server) of
        undefined -> 
            {ok, Pid} = chat_server:start_link(),
            Pid;
        Pid -> 
            Pid
    end,

    %% Start three client processes
    Client1 = chat_client:start_link(),
    Client2 = chat_client:start_link(),
    Client3 = chat_client:start_link(),

    io:format("Connecting clients to server...~n"),
    chat_client:connect(ChatServer, Client1),
    chat_client:connect(ChatServer, Client2),
    chat_client:connect(ChatServer, Client3),

    io:format("Sending messages...~n"),
    chat_client:send_message(ChatServer, "Hello from Client 1!"),
    chat_client:send_message(ChatServer, "Hello from Client 2!"),
    chat_client:send_message(ChatServer, "Hello from Client 3!"),

    %% Keep running to allow message passing
    timer:sleep(2000),  %% Wait for messages to be processed
    io:format("Chat completed.~n").
