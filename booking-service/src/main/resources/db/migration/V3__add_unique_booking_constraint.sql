CREATE UNIQUE INDEX uq_court_date_time_confirmed
    ON bookings (court_id, date, start_time)
    WHERE status = 'CONFIRMED';