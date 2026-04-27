import React, { useMemo, useState } from 'react';
import { SafeAreaView, Text, TextInput, TouchableOpacity, View, StyleSheet } from 'react-native';

export default function App() {
  const [weight, setWeight] = useState('');
  const [height, setHeight] = useState('');
  const [result, setResult] = useState(null);

  const category = useMemo(() => {
    if (result == null) return '';
    if (result < 18.5) return 'Underweight';
    if (result < 25) return 'Normal';
    if (result < 30) return 'Overweight';
    return 'Obese';
  }, [result]);

  const calculateBMI = () => {
    const w = parseFloat(weight);
    const h = parseFloat(height);

    if (!w || !h || h <= 0) {
      setResult(null);
      return;
    }

    const bmi = w / ((h / 100) * (h / 100));
    setResult(Number(bmi.toFixed(2)));
  };

  const reset = () => {
    setWeight('');
    setHeight('');
    setResult(null);
  };

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.title}>BMI Calculator</Text>

      <TextInput
        style={styles.input}
        placeholder="Weight (kg)"
        keyboardType="numeric"
        value={weight}
        onChangeText={setWeight}
      />

      <TextInput
        style={styles.input}
        placeholder="Height (cm)"
        keyboardType="numeric"
        value={height}
        onChangeText={setHeight}
      />

      <TouchableOpacity style={styles.btn} onPress={calculateBMI}>
        <Text style={styles.btnText}>Calculate</Text>
      </TouchableOpacity>

      <TouchableOpacity style={[styles.btn, styles.btnAlt]} onPress={reset}>
        <Text style={styles.btnText}>Reset</Text>
      </TouchableOpacity>

      {result !== null && (
        <View style={styles.resultBox}>
          <Text style={styles.resultText}>BMI: {result}</Text>
          <Text style={styles.resultText}>Category: {category}</Text>
        </View>
      )}
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#eef2ff',
  },
  title: {
    fontSize: 28,
    fontWeight: '700',
    textAlign: 'center',
    marginBottom: 20,
  },
  input: {
    borderWidth: 1,
    borderColor: '#cbd5e1',
    borderRadius: 10,
    padding: 12,
    marginBottom: 12,
    backgroundColor: '#fff',
  },
  btn: {
    backgroundColor: '#1d4ed8',
    padding: 12,
    borderRadius: 10,
    marginTop: 8,
  },
  btnAlt: {
    backgroundColor: '#475569',
  },
  btnText: {
    color: '#fff',
    textAlign: 'center',
    fontWeight: '700',
  },
  resultBox: {
    marginTop: 20,
    padding: 16,
    borderRadius: 12,
    backgroundColor: '#fff',
  },
  resultText: {
    fontSize: 18,
    marginBottom: 6,
    fontWeight: '600',
  },
});
