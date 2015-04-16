package cx.criticalWay

import com.google.common.collect.HashBasedTable

/**

 */
class Main {
    def connections = HashBasedTable.create()
    private Random random = new Random()

    Main() {
        connections.put(City.S, City.K, 70);
        connections.put(City.S, City.T, 132);
        connections.put(City.S, City.E, 168);
        connections.put(City.S, City.O, 90);
        connections.put(City.K, City.T, 101);
        connections.put(City.K, City.R, 146);
        connections.put(City.R, City.T, 134);
        connections.put(City.T, City.L, 141);
        connections.put(City.R, City.L, 140);
        connections.put(City.W, City.T, 154);
        connections.put(City.W, City.L, 154);
        connections.put(City.W, City.B, 175);
        connections.put(City.W, City.N, 115);
        connections.put(City.W, City.C, 227);
        connections.put(City.W, City.E, 118);
        connections.put(City.B, City.L, 213);
        connections.put(City.B, City.N, 193);
        connections.put(City.C, City.N, 178);
        connections.put(City.G, City.N, 136);
        connections.put(City.G, City.C, 142);
        connections.put(City.G, City.Z, 284);
        connections.put(City.G, City.P, 243);
        connections.put(City.C, City.E, 180);
        connections.put(City.C, City.P, 108);
        connections.put(City.E, City.P, 186);
        connections.put(City.E, City.O, 167);
        connections.put(City.P, City.O, 204);
        connections.put(City.P, City.F, 119);
        connections.put(City.P, City.D, 144);
        connections.put(City.P, City.Z, 193);
        connections.put(City.O, City.D, 78);
        connections.put(City.D, City.F, 217);
        connections.put(City.F, City.Z, 98);
    }

    public static void main(String[] args) {
        def main = new Main();

        while (true) {
            try {
                def trace = main.createTrace(City.S, City.G);
                if (trace.size() == 16) {
                    main.printTrace(trace)
                    println()
                }
            } catch(IndexOutOfBoundsException e) {

            }

        }
    }

    def printTrace(List<City> cities) {
        def length = 0;

        City previousCity = null;

        def iterator = cities.iterator();
        while (iterator.hasNext()) {
            def city = iterator.next()
            print city.name()
            if (iterator.hasNext()) print " -> "

            if (previousCity && connections.contains(city, previousCity)) length += connections.get(city, previousCity);
            else if (previousCity && connections.contains(previousCity, city)) length += connections.get(previousCity, city);
            else if (previousCity) throw new Exception("conection not found")

            previousCity = city;
        }

        print " " + length
    }

    List<City> createTrace(City i, City o) {
        def trace = new ArrayList<City>();

        while (true) {

            trace.add(i);
            if (o.equals(i)) break;

            def ways = getOutWays(i);
            if (ways.contains(i)) ways.remove(i);

            while(!ways.isEmpty()) {
                City object = ways.get(random.nextInt() % ways.size());

                if (trace.contains(object)) {
                    ways.remove(object);
                    if (ways.isEmpty()) throw new IndexOutOfBoundsException();
                } else {
                    i = object;
                    break;
                }
            }
        }

        return trace;
    }

    def getOutWays(City city) {
        def ways = new HashSet<City>();

        connections.rowMap().getOrDefault(city, Collections.emptyMap()).keySet().each { City c -> ways.add(c); }
        connections.columnMap().getOrDefault(city, Collections.emptyMap()).keySet().each { City c -> ways.add(c); }

        return ways.asList();
    }
}
