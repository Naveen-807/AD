import 'package:flutter/material.dart';

void main() {
  runApp(const ExpenseApp());
}

class ExpenseApp extends StatelessWidget {
  const ExpenseApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Expense Manager',
      theme: ThemeData(useMaterial3: true, colorSchemeSeed: Colors.teal),
      home: const ExpenseHomePage(),
    );
  }
}

class ExpenseItem {
  final String title;
  final double amount;
  final DateTime date;

  ExpenseItem({required this.title, required this.amount, required this.date});
}

class ExpenseHomePage extends StatefulWidget {
  const ExpenseHomePage({super.key});

  @override
  State<ExpenseHomePage> createState() => _ExpenseHomePageState();
}

class _ExpenseHomePageState extends State<ExpenseHomePage> {
  final List<ExpenseItem> _expenses = [];

  double get _total => _expenses.fold(0, (sum, e) => sum + e.amount);

  void _addExpense() {
    final titleController = TextEditingController();
    final amountController = TextEditingController();

    showDialog<void>(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: const Text('Add Expense'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              TextField(
                controller: titleController,
                decoration: const InputDecoration(labelText: 'Title'),
              ),
              TextField(
                controller: amountController,
                keyboardType: const TextInputType.numberWithOptions(decimal: true),
                decoration: const InputDecoration(labelText: 'Amount'),
              ),
            ],
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(context),
              child: const Text('Cancel'),
            ),
            ElevatedButton(
              onPressed: () {
                final title = titleController.text.trim();
                final amount = double.tryParse(amountController.text.trim());

                if (title.isEmpty || amount == null || amount <= 0) {
                  return;
                }

                setState(() {
                  _expenses.insert(
                    0,
                    ExpenseItem(title: title, amount: amount, date: DateTime.now()),
                  );
                });

                Navigator.pop(context);
              },
              child: const Text('Add'),
            ),
          ],
        );
      },
    );
  }

  void _removeExpense(int index) {
    setState(() {
      _expenses.removeAt(index);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Expense Manager'),
        actions: [
          IconButton(onPressed: _addExpense, icon: const Icon(Icons.add)),
        ],
      ),
      body: Column(
        children: [
          Container(
            width: double.infinity,
            padding: const EdgeInsets.all(16),
            color: Colors.teal.shade50,
            child: Text(
              'Total: Rs ${_total.toStringAsFixed(2)}',
              style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
          ),
          Expanded(
            child: _expenses.isEmpty
                ? const Center(child: Text('No expenses yet. Tap + to add.'))
                : ListView.builder(
                    itemCount: _expenses.length,
                    itemBuilder: (context, index) {
                      final e = _expenses[index];
                      return Dismissible(
                        key: ValueKey('${e.title}-${e.date.microsecondsSinceEpoch}'),
                        background: Container(color: Colors.red),
                        onDismissed: (_) => _removeExpense(index),
                        child: ListTile(
                          title: Text(e.title),
                          subtitle: Text(
                            '${e.date.day}/${e.date.month}/${e.date.year}',
                          ),
                          trailing: Text(
                            'Rs ${e.amount.toStringAsFixed(2)}',
                            style: const TextStyle(fontWeight: FontWeight.bold),
                          ),
                        ),
                      );
                    },
                  ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _addExpense,
        child: const Icon(Icons.add),
      ),
    );
  }
}
