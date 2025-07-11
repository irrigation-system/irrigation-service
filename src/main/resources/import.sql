INSERT INTO crop (id, name, min_allowed_moisture, coefficient_dev, coefficient_mid, coefficient_late, dev_num_of_days,
                  mid_num_of_days, lat_num_of_days)
VALUES ('4a2b1c46-f0af-4579-a002-ed5c0a096393', 'lettuce', 50.0, 0.7, 1.0, 0.95, 17, 28, 14);

INSERT INTO public.location (latitude, longitude, id)
VALUES (46.056946, 14.505751, '89eb42d4-8f22-4a6d-a73b-4aab6ca14fc9');

INSERT INTO public.irrigation_data (id, irrigation_start, monthly_rainfall_month, monthly_rainfall, cultivation_area)
VALUES ('59e142d4-8f22-4a6d-a73b-4aab6ca14fc9', '2025-05-10 16:32:14.000000', '2025-05', 53.5, 0.22);

INSERT INTO public.user_data (cropentity_id, id, irrigation_id, location_id, user_token)
VALUES ('4a2b1c46-f0af-4579-a002-ed5c0a096393', 'fe7f485a-c73c-4b73-b1f9-591506fcee41', '59e142d4-8f22-4a6d-a73b-4aab6ca14fc9',
        '89eb42d4-8f22-4a6d-a73b-4aab6ca14fc9', '123');
