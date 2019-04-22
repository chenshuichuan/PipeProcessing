package com.ruoyi.project.algorithm.genetic;

import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Population {
    private Individual population[];
    private double populationFitness = -1;

    /**
     * Initializes blank population of individuals
     *
     * @param populationSize The size of the population
     */
    public Population(int populationSize) {
        // Initial population
        this.population = new Individual[populationSize];
    }
    public Population(int populationSize, List<TaoliaoOrigin> taoliaoOrigins1, List<TaoliaoResult> taoliaoResultList1) {
        // Initial population
        this.population = new Individual[populationSize];

        // Loop over population size
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            // Create individual
            Individual individual = new Individual(taoliaoOrigins1,taoliaoResultList1);
            // Add individual to population
            this.population[individualCount] = individual;
        }
    }

    /**
     * Get individuals from the population
     *
     * @return individuals Individuals in population
     */
    public Individual[] getIndividuals() {
        return this.population;
    }

    /**
     * Find fittest individual in the population
     * 将种群中的所有个体，按照适应度从大到小排序
     * @param offset index
     * @return individual Fittest individual at offset
     */
    public Individual getFittest(int offset) {
        // Order population by fitness
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });
        // Return the fittest individual
        return this.population[offset];
    }

    /**
     * Set population's fitness
     *
     * @param fitness
     *            The population's total fitness
     */
    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    /**
     * Get population's fitness
     *
     * @return populationFitness The population's total fitness
     */
    public double getPopulationFitness() {
        return this.populationFitness;
    }

    /**
     * Get population's size
     *
     * @return size The population's size
     */
    public int size() {
        return this.population.length;
    }

    /**
     * Set individual at offset
     *
     * @param individual
     * @param offset
     * @return individual
     */
    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

    /**
     * Get individual at offset
     *
     * @param offset
     * @return individual
     */
    public Individual getIndividual(int offset) {
        return population[offset];
    }

    /**
     * Shuffles the population in-place
     * @return void
     */
    public void shuffle() {
        Random rnd = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Individual a = population[index];
            population[index] = population[i];
            population[i] = a;
        }
    }

    public void crossExchangeGen(Individual parent1, Individual parent2){

//        if (0.5 > Math.random()) {
//            offspring.setGene(geneIndex, );
//        } else {
//            offspring.setGene(geneIndex, parent2.getGene(geneIndex));
//        }
    }
}