% Compile: erlc chat_server.erl chat_client.erl chat_script.erl
% Execute: erl -noshell -s chat_script start -s init stop
-module(chat_server).
-behaviour(gen_server).

%% Exported functions
-export([start_link/0]).

%% Callbacks for gen_server
-export([init/1, handle_info/2, handle_call/3, handle_cast/2, terminate/2]).

%% Start the gen_server
start_link() ->
    gen_server:start_link({local, chat_server}, ?MODULE, [], []).

%% Initialize the server state with an empty list of clients
init([]) ->
    {ok, []}.

%% Handle incoming messages
handle_info({connect, ClientPid}, Clients) ->
    %% Add the new client to the list of connected clients
    io:format("Client ~p connected.~n", [ClientPid]),
    {noreply, [ClientPid | Clients]};

handle_info({send_message, SenderPid, Message}, Clients) ->
    %% Broadcast the message to all clients except the sender
    lists:foreach(
        fun(ClientPid) ->
            if
                ClientPid /= SenderPid ->
                    ClientPid ! {receive_message, Message};
                true ->
                    ok
            end
        end,
        Clients
    ),
    {noreply, Clients};

handle_info(_, Clients) ->
    %% Ignore unexpected messages
    {noreply, Clients}.

%% No-op for handle_call/3
handle_call(_Request, _From, State) ->
    {reply, ok, State}.

%% No-op for handle_cast/2
handle_cast(_Request, State) ->
    {noreply, State}.

%% No-op for terminate/2
terminate(_Reason, _State) ->
    ok.
