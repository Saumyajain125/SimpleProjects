import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<int> _gameState;
  int _winner;
  bool _isCross;

  @override
  void initState() {
    super.initState();
    _gameState = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    _winner = 0;
    _isCross = true;
  }

  void _resetGame() {
    setState(() {
      _gameState = [0, 0, 0, 0, 0, 0, 0, 0, 0];
      _winner = 0;
      _isCross = true;
    });
  }

  void _playGame(int index) {
    if (this._winner != 0) {
      return;
    }
    if (this._gameState[index] == 0) {
      setState(() {
        if (this._isCross) {
          this._gameState[index] = 1;
        } else {
          this._gameState[index] = 2;
        }
        this._isCross = !this._isCross;
        this._checkWin();
      });
    }
  }

  void _checkWin() {
    if ((_gameState[0] != 0) &&
        (_gameState[0] == _gameState[1]) &&
        (_gameState[1] == _gameState[2])) {
      setState(() {
        this._winner = this._gameState[0];
      });
    } else if ((_gameState[3] != 0) &&
        (_gameState[3] == _gameState[4]) &&
        (_gameState[4] == _gameState[5])) {
      setState(() {
        this._winner = this._gameState[3];
      });
    } else if ((_gameState[6] != 0) &&
        (_gameState[6] == _gameState[7]) &&
        (_gameState[7] == _gameState[8])) {
      setState(() {
        this._winner = this._gameState[6];
      });
    } else if ((_gameState[0] != 0) &&
        (_gameState[0] == _gameState[3]) &&
        (_gameState[3] == _gameState[6])) {
      setState(() {
        this._winner = this._gameState[0];
      });
    } else if ((_gameState[1] != 0) &&
        (_gameState[1] == _gameState[4]) &&
        (_gameState[4] == _gameState[7])) {
      setState(() {
        this._winner = this._gameState[1];
      });
    } else if ((_gameState[2] != 0) &&
        (_gameState[2] == _gameState[5]) &&
        (_gameState[5] == _gameState[8])) {
      setState(() {
        this._winner = this._gameState[2];
      });
    } else if ((_gameState[0] != 0) &&
        (_gameState[0] == _gameState[4]) &&
        (_gameState[4] == _gameState[8])) {
      setState(() {
        this._winner = this._gameState[0];
      });
    } else if ((_gameState[2] != 0) &&
        (_gameState[2] == _gameState[4]) &&
        (_gameState[4] == _gameState[6])) {
      setState(() {
        this._winner = this._gameState[2];
      });
    } else if (!_gameState.contains(0)) {
      setState(() {
        this._winner = 3;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    String winnerText;
    if (this._winner == 1) {
      winnerText = "The Winner is Cross";
    } else if (this._winner == 2) {
      winnerText = "The Winner is Circle";
    } else if (this._winner == 3) {
      winnerText = "Game Draw";
    } else {
      winnerText = "The Game is Running";
    }

    String currentRunText = this._isCross ? "Cross" : "Circle";
    return Scaffold(
      appBar: AppBar(
        title: Text('Tic Tac Toe App'),
      ),
      body: Column(
        children: [
          Container(
            alignment: Alignment.center,
            child: Text("Current Run: $currentRunText"),
          ),
          Expanded(
            child: GridView.count(
              crossAxisCount: 3,
              children: List<Widget>.generate(
                this._gameState.length,
                (index) {
                  IconData icon;
                  if (this._gameState[index] == 1) {
                    icon = Icons.cancel;
                  } else if (this._gameState[index] == 2) {
                    icon = Icons.circle;
                  } else {
                    icon = Icons.check_box_outline_blank;
                  }

                  return GestureDetector(
                    onTap: () {
                      _playGame(index);
                    },
                    child: Container(
                      alignment: Alignment.center,
                      child: Icon(
                        icon,
                        size: 50,
                      ),
                    ),
                  );
                },
              ),
            ),
          ),
          Container(
            padding: EdgeInsets.symmetric(vertical: 10),
            child: Text(winnerText),
          ),
          ElevatedButton(
            onPressed: _resetGame,
            child: Text("Reset Game"),
          ),
        ],
      ),
    );
  }
}