package com.ruoyi.project.algorithm.genetic;


import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    protected int tournamentSize;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount,
                            int tournamentSize) {

        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.tournamentSize = tournamentSize;
    }


    public Population initPopulation(List<TaoliaoOrigin> taoliaoOrigins1, List<TaoliaoResult> taoliaoResultList1) {
        // Initialize population
        Population population = new Population(this.populationSize, taoliaoOrigins1, taoliaoResultList1);
        return population;
    }

    /**
     * Check if population has met termination condition
     * @param generationsCount Number of generations passed
     * @param maxGenerations   Number of generations to terminate after
     * @return boolean True if termination condition met, otherwise, false
     */
    public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
        return (generationsCount > maxGenerations);
    }

    /**
     * Check if population has met termination condition
     * @param population
     * @return boolean True if termination condition met, otherwise, false
     */
    public boolean isTerminationConditionMet(Population population) {
        return population.getFittest(0).getFitness() == 100.0;
    }

    /**
     * Calculate individual's fitness value
     * @param individual
     * @return fitness
     */
    public double calcFitness(Individual individual) {
        individual.calcFitness();
        return individual.getFitness();
    }

    /**
     * Evaluate population
     * @param population
     */
    public void evalPopulation(Population population) {
        double populationFitness = 0;

        // Loop over population evaluating individuals and summing population
        // fitness
        for (Individual individual : population.getIndividuals()) {
            populationFitness += this.calcFitness(individual);
        }

        population.setPopulationFitness(populationFitness);
    }
    /**
     * Selects parent for crossover using tournament selection
     * @param population
     * @return The individual selected as a parent
     */
    public Individual selectParent(Population population) {
        // Create tournament
        Population tournament = new Population(this.tournamentSize);
        // Add random individuals to the tournamen
        // population.shuffle();
        //这样就不用每次重新洗一次了
        Map<Integer,Integer> integerMap = new HashMap<>();
        while(integerMap.keySet().size()<this.tournamentSize){
            int index = (int)(Math.random()*populationSize);
            integerMap.put(index,index);
        }
        int i=0;
        for (Integer key: integerMap.keySet()) {
            Individual tournamentIndividual = population.getIndividual(integerMap.get(key));
            tournament.setIndividual(i, tournamentIndividual);
            i++;
        }
        // Return the best
        return tournament.getFittest(0);
    }


    /**
     * Apply crossover to population
     *以parent1，parent1都是后代，交叉之后就是自己的后代
     * 直接在原种群上作更改
     * @param population The population to apply crossover to
     * @return The new population
     */
    public Population crossoverPopulation(Population population) {

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            //这一步可优化
            Individual parent1 = population.getFittest(populationIndex);
            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                // Find second parent
                Individual parent2 = selectParent(population);
                //交换基因

            }

        }

        return population;
    }
}
