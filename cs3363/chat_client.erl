% Compile: erlc chat_server.erl chat_client.erl chat_script.erl
% Execute: erl -noshell -s chat_script start -s init stop
-module(chat_client).
-export([start_link/0, connect/2, send_message/2]).

%% Start the client process and enter its loop
start_link() ->
    spawn(fun loop/0).

%% Connect the client to the server
connect(ChatServer, ClientPid) ->
    ChatServer ! {connect, ClientPid}.

%% Send a message to the chat server
send_message(ChatServer, Message) ->
    io:format("Client ~p sending message: ~s~n", [self(), Message]),
    ChatServer ! {send_message, self(), Message}.

%% Client process loop to handle incoming messages
loop() ->
    receive
        {chat_message, Message} ->
	    io:format("Client ~p received: ~s~n", [self(), Message]),
            loop();
        stop ->
            io:format("Client ~p shutting down~n", [self()]),
            ok;
        _ ->
            loop() % Handle unexpected messages gracefully
    end.
